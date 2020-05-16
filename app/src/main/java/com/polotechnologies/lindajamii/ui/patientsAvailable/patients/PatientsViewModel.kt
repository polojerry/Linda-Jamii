package com.polotechnologies.lindajamii.ui.patientsAvailable.patients

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails
import com.polotechnologies.lindajamii.database.LindaJamiiDatabase.Companion.getDatabase
import com.polotechnologies.lindajamii.repository.PatientRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PatientsViewModel(application: Application) : ViewModel() {

    //Selected Patient
    private val _selectedPatient = MutableLiveData<ExpectantDetails>()
    val selectedPatient : LiveData<ExpectantDetails>
        get () = _selectedPatient

    private val _repoIsLoading = MutableLiveData<Boolean>()
    val repoIsLoading : LiveData<Boolean>
        get () = _repoIsLoading

    private var viewModelJob = Job()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val database = getDatabase(application)
    val patientRepository = PatientRepository(database)
    val patientsListData = patientRepository.patients


    init{
        fetchPatients()
        _repoIsLoading.value = true
    }


    fun fetchPatients()  = viewModelScope.launch{
        patientRepository.refreshPatients()
        _repoIsLoading.value = false
    }

    fun displaySelectedPatient(expectantDetails: ExpectantDetails){
        _selectedPatient.value = expectantDetails
    }

    fun displaySelectedPatientDone(){
        _selectedPatient.value = null
    }

    override fun onCleared() {
        viewModelJob.cancel()
    }
}




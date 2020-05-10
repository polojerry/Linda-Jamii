package com.polotechnologies.lindajamii.ui.patients

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.firebase.ui.firestore.paging.LoadingState
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails
import com.polotechnologies.lindajamii.database.LindaJamiiDatabase.Companion.getDatabase
import com.polotechnologies.lindajamii.databinding.FragmentPatientsBinding
import com.polotechnologies.lindajamii.network.FirestoreServiceViewModel
import com.polotechnologies.lindajamii.repository.PatientRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PatientsViewModel(application: Application,
                        firestoreServiceViewModel: FirestoreServiceViewModel) : ViewModel() {

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
    val patientRepository = PatientRepository(firestoreServiceViewModel,database)

    init{
        fetchPatients()
        _repoIsLoading.value = true
    }

    fun fetchPatients()  = viewModelScope.launch{
        patientRepository.refreshPatients()
        _repoIsLoading.value = false
    }

    val patientsListData = patientRepository.patients

    override fun onCleared() {
        viewModelJob.cancel()
    }
}




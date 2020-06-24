package com.polotechnologies.lindajamii.ui.patients

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails
import com.polotechnologies.lindajamii.database.LindaJamiiDatabase
import com.polotechnologies.lindajamii.repository.PatientRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PatientsViewModel(application: Application) :
    AndroidViewModel(application) {

    private val patientsRepository = PatientRepository(LindaJamiiDatabase.getDatabase(application))

    //Selected Patient
    private val _selectedPatient = MutableLiveData<ExpectantDetails?>()
    val selectedPatient: LiveData<ExpectantDetails?>
        get() = _selectedPatient

    val patientsData = patientsRepository.patients

    init {
        fetchPatients()
    }

    fun fetchPatients() = CoroutineScope(Dispatchers.IO).launch {
        patientsRepository.refreshPatients("")
    }

    fun displaySelectedPatient(expectantDetails: ExpectantDetails) {
        _selectedPatient.value = expectantDetails
    }

    fun displaySelectedPatientDone() {
        _selectedPatient.value = null
    }

}




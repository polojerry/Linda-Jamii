package com.polotechnologies.lindajamii.ui.patients

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails
import com.polotechnologies.lindajamii.database.LindaJamiiDatabase
import com.polotechnologies.lindajamii.network.FirestoreService
import com.polotechnologies.lindajamii.repository.PatientRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PatientsViewModel(application: Application) :
    AndroidViewModel(application) {

    val patientsRepository = PatientRepository(LindaJamiiDatabase.getDatabase(application))

    val firestoreService = FirestoreService()

    //Selected Patient
    private val _selectedPatient = MutableLiveData<ExpectantDetails?>()
    val selectedPatient: LiveData<ExpectantDetails?>
        get() = _selectedPatient

    val patientsData = patientsRepository.patients

    init {
        CoroutineScope(Dispatchers.IO).launch {
            patientsRepository.refreshPatients("")
        }
    }
    fun fetchPatients() = firestoreService.getAllPatients("")

    fun displaySelectedPatient(expectantDetails: ExpectantDetails) {
        _selectedPatient.value = expectantDetails
    }

    fun displaySelectedPatientDone() {
        _selectedPatient.value = null
    }

}




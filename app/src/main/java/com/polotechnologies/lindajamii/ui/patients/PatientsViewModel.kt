package com.polotechnologies.lindajamii.ui.patients

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails
import com.polotechnologies.lindajamii.network.FirestoreService

class PatientsViewModel(application: Application) :
    ViewModel() {

    val firestoreService = FirestoreService()

    //Selected Patient
    private val _selectedPatient = MutableLiveData<ExpectantDetails?>()
    val selectedPatient: LiveData<ExpectantDetails?>
        get() = _selectedPatient


    fun fetchPatients() = firestoreService.getAllPatients("")

    fun displaySelectedPatient(expectantDetails: ExpectantDetails) {
        _selectedPatient.value = expectantDetails
    }

    fun displaySelectedPatientDone() {
        _selectedPatient.value = null
    }

}




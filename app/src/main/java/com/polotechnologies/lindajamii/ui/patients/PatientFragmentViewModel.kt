package com.polotechnologies.lindajamii.ui.patients

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polotechnologies.lindajamii.dataModels.HomeOption
import com.polotechnologies.lindajamii.dataModels.Patients

internal class PatientFragmentViewModel : ViewModel() {

    //selected Patient
    private val _selectedPatient = MutableLiveData<Patients>()
    val selectedPatient: LiveData<Patients>
        get() = _selectedPatient


    fun displaySelectedPatient(patients: Patients) {
        _selectedPatient.value = patients
    }

    fun displaySelectedPatientComplete() {
        _selectedPatient.value = null
    }

}
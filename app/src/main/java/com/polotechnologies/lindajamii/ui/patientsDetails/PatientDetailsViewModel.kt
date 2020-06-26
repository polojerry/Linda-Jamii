package com.polotechnologies.lindajamii.ui.patientsDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails
import com.polotechnologies.lindajamii.database.ExpectantDetailsDao
import kotlinx.coroutines.Job

class PatientDetailsViewModel(val ancNumber : String, private val expectantDetailsDao: ExpectantDetailsDao) : ViewModel() {

    //Patient Details
    private val _patientDetails = MutableLiveData<LiveData<ExpectantDetails>>()
    val patientDetails : LiveData<ExpectantDetails>
        get () = _patientDetails.value!!

    private var viewModelJob = Job()

    init{
        fetchPatient(ancNumber)
    }


    private fun fetchPatient(ancNumber: String){
        _patientDetails.value = expectantDetailsDao.getPatient(ancNumber)
    }

    override fun onCleared() {
        viewModelJob.cancel()
    }
}




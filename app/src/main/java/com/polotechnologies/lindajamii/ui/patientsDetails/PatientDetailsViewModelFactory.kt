package com.polotechnologies.lindajamii.ui.patientsDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.polotechnologies.lindajamii.database.ExpectantDetailsDao

class PatientDetailsViewModelFactory (private  val ancNumber : String, private val expectantDetailsDao: ExpectantDetailsDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PatientDetailsViewModel::class.java)) {
            return PatientDetailsViewModel(
                ancNumber,
                expectantDetailsDao
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
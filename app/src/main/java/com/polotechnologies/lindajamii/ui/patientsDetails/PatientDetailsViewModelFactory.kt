package com.polotechnologies.lindajamii.ui.patientsDetails

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.polotechnologies.lindajamii.database.ExpectantDetailsDao

class PatientDetailsViewModelFactory ( val ancNumber : String, val expectantDetailsDao: ExpectantDetailsDao
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
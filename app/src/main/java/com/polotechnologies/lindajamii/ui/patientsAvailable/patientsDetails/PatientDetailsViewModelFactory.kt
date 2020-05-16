package com.polotechnologies.lindajamii.ui.patientsAvailable.patientsDetails

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.polotechnologies.lindajamii.database.ExpectantDetailsDao

class PatientDetailsViewModelFactory (val app : Application, val ancNumber : String, val expectantDetailsDao: ExpectantDetailsDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PatientDetailsViewModel::class.java)) {
            return PatientDetailsViewModel(
                app,
                ancNumber,
                expectantDetailsDao
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
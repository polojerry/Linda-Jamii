package com.polotechnologies.lindajamii.ui.initialvisit

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.polotechnologies.lindajamii.database.PatientProfileDAO

/**
 * Simple ViewModel factory for Initial Visit ViewModel
 */
internal class InitialVisitViewModelFactory(
    private val application: Application,
    private val database: PatientProfileDAO
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InitialVisitViewModel::class.java)) {
            return InitialVisitViewModel(
                application,
                database
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

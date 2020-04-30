/*
package com.polotechnologies.lindajamii.ui.subsequentVisits

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.polotechnologies.lindajamii.database.PatientProfileDAO
import com.polotechnologies.lindajamii.database.SubsequentDAO

*/
/**
 * Simple ViewModel factory for Initial Visit ViewModel
 *//*

class SubsequentVisitViewModelFactory(
    private val application: Application,
    private val database: SubsequentDAO
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
*/

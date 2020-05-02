package com.polotechnologies.lindajamii.ui.subsequentVisits

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.polotechnologies.lindajamii.database.PatientProfileDAO
import com.polotechnologies.lindajamii.databinding.FragmentSubsequentVisitsBinding

/**
 * Simple ViewModel factory for Initial Visit ViewModel
 */

class SubsequentVisitViewModelFactory(
    private val mDatabase: FirebaseFirestore,
    val mBinding: FragmentSubsequentVisitsBinding
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubsequentVisitViewModel::class.java)) {
            return SubsequentVisitViewModel(
                mDatabase,
                mBinding
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


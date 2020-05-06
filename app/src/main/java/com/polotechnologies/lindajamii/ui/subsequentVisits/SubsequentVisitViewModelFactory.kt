package com.polotechnologies.lindajamii.ui.subsequentVisits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.polotechnologies.lindajamii.databinding.FragmentSubsequentVisitsBinding
import com.polotechnologies.lindajamii.network.FirestoreServiceViewModel

/**
 * Simple ViewModel factory for Initial Visit ViewModel
 */

class SubsequentVisitViewModelFactory(
    val mBinding: FragmentSubsequentVisitsBinding,
    private val firestoreServiceViewModel: FirestoreServiceViewModel
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubsequentVisitViewModel::class.java)) {
            return SubsequentVisitViewModel(
                mBinding,
                firestoreServiceViewModel
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


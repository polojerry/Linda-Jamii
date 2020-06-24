package com.polotechnologies.lindajamii.ui.subsequentVisits

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.polotechnologies.lindajamii.databinding.FragmentSubsequentVisitsBinding

/**
 * Simple ViewModel factory for Initial Visit ViewModel
 */

class SubsequentVisitViewModelFactory(
    val application: Application,
    val mBinding: FragmentSubsequentVisitsBinding
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubsequentVisitViewModel::class.java)) {
            return SubsequentVisitViewModel(
                application,
                mBinding
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


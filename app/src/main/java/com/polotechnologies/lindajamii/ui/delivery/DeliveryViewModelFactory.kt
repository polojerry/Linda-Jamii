package com.polotechnologies.lindajamii.ui.delivery

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.polotechnologies.lindajamii.database.PatientProfileDAO
import com.polotechnologies.lindajamii.databinding.FragmentDeliveryBinding
import com.polotechnologies.lindajamii.databinding.FragmentSubsequentVisitsBinding

/**
 * Simple ViewModel factory for Devilery Visit ViewModel
 */

class DeliveryViewModelFactory(
    private val mDatabase: FirebaseFirestore,
    val mBinding: FragmentDeliveryBinding
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DeliveryViewModel::class.java)) {
            return DeliveryViewModel(
                mDatabase,
                mBinding
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


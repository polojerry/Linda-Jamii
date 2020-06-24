package com.polotechnologies.lindajamii.ui.delivery

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.polotechnologies.lindajamii.databinding.FragmentDeliveryBinding

/**
 * Simple ViewModel factory for Devilery Visit ViewModel
 */

class DeliveryViewModelFactory(
    val application: Application,
    val mBinding: FragmentDeliveryBinding
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DeliveryViewModel::class.java)) {
            return DeliveryViewModel(
                application,
                mBinding
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


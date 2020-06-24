package com.polotechnologies.lindajamii.ui.initialvisit.medicalSurgicalHistory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.polotechnologies.lindajamii.databinding.FragmentMedicalSurgicalHistoryBinding

class MedicalSurgicalHistoryViewModelFactory (val application: Application,
                                              private val mBinding: FragmentMedicalSurgicalHistoryBinding,
private val mUserId: String) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MedicalSurgicalHistoryViewModel::class.java)) {
                return MedicalSurgicalHistoryViewModel(
                    application,
                    mBinding,
                    mUserId
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}
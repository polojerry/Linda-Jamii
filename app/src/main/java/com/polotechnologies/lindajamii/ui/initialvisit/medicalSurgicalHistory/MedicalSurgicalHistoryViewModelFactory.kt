package com.polotechnologies.lindajamii.ui.initialvisit.medicalSurgicalHistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.polotechnologies.lindajamii.databinding.FragmentMaternalProfileBinding
import com.polotechnologies.lindajamii.databinding.FragmentMedicalSurgicalHistoryBinding

class MedicalSurgicalHistoryViewModelFactory (private val mDatabase: FirebaseFirestore,
                                              private val mBinding: FragmentMedicalSurgicalHistoryBinding,
private val mUserId: String) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MedicalSurgicalHistoryViewModel::class.java)) {
                return MedicalSurgicalHistoryViewModel(
                    mDatabase,
                    mBinding,
                    mUserId
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}
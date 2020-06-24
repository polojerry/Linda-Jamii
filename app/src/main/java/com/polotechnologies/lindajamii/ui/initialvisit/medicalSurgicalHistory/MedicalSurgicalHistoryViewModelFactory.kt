package com.polotechnologies.lindajamii.ui.initialvisit.medicalSurgicalHistory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.polotechnologies.lindajamii.databinding.FragmentMaternalProfileBinding
import com.polotechnologies.lindajamii.databinding.FragmentMedicalSurgicalHistoryBinding
import com.polotechnologies.lindajamii.network.FirestoreServiceViewModel

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
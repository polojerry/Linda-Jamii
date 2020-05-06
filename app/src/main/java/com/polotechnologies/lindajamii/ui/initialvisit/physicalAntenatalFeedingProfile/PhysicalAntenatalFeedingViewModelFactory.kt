package com.polotechnologies.lindajamii.ui.initialvisit.physicalAntenatalFeedingProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.polotechnologies.lindajamii.databinding.FragmentMaternalProfileBinding
import com.polotechnologies.lindajamii.databinding.FragmentMedicalSurgicalHistoryBinding
import com.polotechnologies.lindajamii.databinding.FragmentPhysicalAntenatalFeedingBinding
import com.polotechnologies.lindajamii.network.FirestoreServiceViewModel

class PhysicalAntenatalFeedingViewModelFactory (val firestoreServiceViewModel: FirestoreServiceViewModel,
                                                private val mBinding: FragmentPhysicalAntenatalFeedingBinding,
                                                private val mUserId : String
) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PhysicalAntenatalFeedingViewModel::class.java)) {
                return PhysicalAntenatalFeedingViewModel(
                    firestoreServiceViewModel,
                    mBinding,
                    mUserId
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}
package com.polotechnologies.lindajamii.ui.initialvisit.maternalProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.polotechnologies.lindajamii.databinding.FragmentMaternalProfileBinding
import com.polotechnologies.lindajamii.network.FirestoreServiceViewModel

class MaternalProfileViewModelFactory (val firestoreServiceViewModel: FirestoreServiceViewModel,
                             private val mBinding: FragmentMaternalProfileBinding) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MaternalProfileViewModel::class.java)) {
                return MaternalProfileViewModel(
                    firestoreServiceViewModel,
                    mBinding
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}
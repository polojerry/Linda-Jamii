package com.polotechnologies.lindajamii.ui.initialvisit.maternalProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.polotechnologies.lindajamii.databinding.FragmentMaternalProfileBinding

class MaternalProfileViewModelFactory (private val mDatabase: FirebaseFirestore,
                             private val mBinding: FragmentMaternalProfileBinding) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MaternalProfileViewModel::class.java)) {
                return MaternalProfileViewModel(
                    mDatabase,
                    mBinding
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}
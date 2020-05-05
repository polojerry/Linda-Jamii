package com.polotechnologies.lindajamii.ui.patients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.polotechnologies.lindajamii.databinding.FragmentMaternalProfileBinding
import com.polotechnologies.lindajamii.databinding.FragmentMedicalSurgicalHistoryBinding
import com.polotechnologies.lindajamii.databinding.FragmentPatientsBinding
import com.polotechnologies.lindajamii.databinding.ItemPatientBinding
import com.polotechnologies.lindajamii.network.FirestoreServiceViewModel

class PatientsViewModelFactory (private val mBinding : FragmentPatientsBinding,
                                private val firestoreServiceViewModel: FirestoreServiceViewModel
                                ) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PatientsViewModel::class.java)) {
                return PatientsViewModel(
                    mBinding,
                    firestoreServiceViewModel
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}
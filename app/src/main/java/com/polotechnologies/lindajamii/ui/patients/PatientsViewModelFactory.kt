package com.polotechnologies.lindajamii.ui.patients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.polotechnologies.lindajamii.databinding.FragmentMaternalProfileBinding
import com.polotechnologies.lindajamii.databinding.FragmentMedicalSurgicalHistoryBinding

class PatientsViewModelFactory (private val mMflNumber : String,  private val mDatabase: FirebaseFirestore
                                ) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PatientsViewModel::class.java)) {
                return PatientsViewModel(
                    mMflNumber,
                    mDatabase
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}
package com.polotechnologies.lindajamii.ui.patientsDetails

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.polotechnologies.lindajamii.databinding.FragmentMaternalProfileBinding
import com.polotechnologies.lindajamii.databinding.FragmentMedicalSurgicalHistoryBinding
import com.polotechnologies.lindajamii.databinding.FragmentPatientsBinding
import com.polotechnologies.lindajamii.databinding.ItemPatientBinding
import com.polotechnologies.lindajamii.network.FirestoreServiceViewModel

class PatientDetailsViewModelFactory (val app : Application, val ancNumber : String
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PatientDetailsViewModel::class.java)) {
            return PatientDetailsViewModel(
                app,
                ancNumber
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
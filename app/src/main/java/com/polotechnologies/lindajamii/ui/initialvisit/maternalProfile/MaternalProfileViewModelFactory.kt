package com.polotechnologies.lindajamii.ui.initialvisit.maternalProfile

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.polotechnologies.lindajamii.databinding.FragmentMaternalProfileBinding

class MaternalProfileViewModelFactory (val application: Application,
                             private val mBinding: FragmentMaternalProfileBinding) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MaternalProfileViewModel::class.java)) {
                return MaternalProfileViewModel(
                    application,
                    mBinding
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}
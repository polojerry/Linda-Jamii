package com.polotechnologies.lindajamii.ui.initialvisit.maternalProfile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.databinding.FragmentMaternalProfileBinding

/**
 .
 */
class MaternalProfileFragment : Fragment() {

    private lateinit var mBinding: FragmentMaternalProfileBinding
    private lateinit var mViewModel: MaternalProfileViewModel

    private lateinit var mDatabase: FirebaseFirestore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_maternal_profile, container, false)
        mDatabase = FirebaseFirestore.getInstance()

        val factory  = MaternalProfileViewModelFactory(mDatabase, mBinding)
        mViewModel = ViewModelProvider(this, factory)[MaternalProfileViewModel::class.java]

        mBinding.buttonNextMedicalSurgicalHistory.setOnClickListener {
            if(!mViewModel.isFieldsEmpty()){
                Toast.makeText(context!!.applicationContext, "All Valid", Toast.LENGTH_SHORT).show()
            }
        }
        return mBinding.root
    }

}
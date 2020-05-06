package com.polotechnologies.lindajamii.ui.initialvisit.maternalProfile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.databinding.FragmentMaternalProfileBinding
import com.polotechnologies.lindajamii.network.FirestoreServiceViewModel

/**
.
 */
class MaternalProfileFragment : Fragment() {

    private lateinit var mBinding: FragmentMaternalProfileBinding
    private lateinit var mViewModel: MaternalProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_maternal_profile, container, false)

        val firestoreServiceViewModel = ViewModelProvider(this)[FirestoreServiceViewModel::class.java]

        val factory = MaternalProfileViewModelFactory(firestoreServiceViewModel, mBinding)
        mViewModel = ViewModelProvider(this, factory)[MaternalProfileViewModel::class.java]

        setObServer()
        mBinding.buttonNextMedicalSurgicalHistory.setOnClickListener {
            if (mViewModel.isFieldsValid()) {
                mBinding.progressBarMaternalProfile.visibility = View.VISIBLE
                mBinding.buttonNextMedicalSurgicalHistory.isEnabled = false
                mViewModel.saveMaternalProfile()

            }
        }
        return mBinding.root
    }

    private fun setObServer() {
        mViewModel.exception.observe(viewLifecycleOwner, Observer { exception ->
            mBinding.progressBarMaternalProfile.visibility = View.INVISIBLE
            if (exception == null) {
                val action =
                    MaternalProfileFragmentDirections.actionMaternalProfileFragmentToMedicalSurgicalHistoryFragment(
                        mViewModel.userId.value!!
                    )
                findNavController().navigate(action)
            } else {
                Toast.makeText(
                    context!!.applicationContext,
                    "Failed: ${exception.localizedMessage}",
                    Toast.LENGTH_SHORT
                )
            }

        })
    }

}
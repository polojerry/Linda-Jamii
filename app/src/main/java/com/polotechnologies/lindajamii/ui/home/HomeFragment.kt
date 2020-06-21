package com.polotechnologies.lindajamii.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.iid.FirebaseInstanceId
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.dataModels.HomeOption
import com.polotechnologies.lindajamii.databinding.FragmentHomeBinding
import com.polotechnologies.lindajamii.util.LindaJamiiFirebaseMessagingService

/**
 * A fragment used to offer option that can be offered to patients
 */
class HomeFragment : Fragment() {
    private val TAG = "HomeFragment"

    lateinit var mBinding: FragmentHomeBinding
    lateinit var mViewModel: HomeFragmentViewModel
    lateinit var mAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        mViewModel = ViewModelProvider(this)[HomeFragmentViewModel::class.java]
        mAuth = FirebaseAuth.getInstance()

        displayOptions()
        setObserver()
        initFcm()
        return mBinding.root
    }

    override fun onResume() {
        super.onResume()
        if (mAuth.currentUser == null) {
            findNavController().navigate(R.id.loginFragment)
        }
    }

    private fun initFcm() {
        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener { instanceTask ->
            if (!instanceTask.isSuccessful) {
                Log.w(TAG, "initFcm: getInstanceFailed: ${instanceTask.exception}")
                return@addOnCompleteListener
            }
            mViewModel.fcmToken = instanceTask.result?.token.toString()
            mViewModel.sendTokenToFirestore(mViewModel.fcmToken)
        }
    }

    private fun displayOptions() {
        mBinding.recyclerHome.adapter =
            HomeFragmentRecyclerAdapter(mViewModel.homeOptions(),
                HomeFragmentRecyclerAdapter.OnClickListener { productCategory ->
                    mViewModel.displaySelectedProductCategory(productCategory)
                })
    }

    private fun setObserver() {
        mViewModel.selectedHomeOption.observe(viewLifecycleOwner, Observer { homeOption ->
            if (homeOption != null) {
                when (homeOption.option_title) {
                    "Initial Visit" -> navigateToOption(R.id.action_homeFragment_to_initialVisitFragment)
                    "Subsequent Visits" -> navigateToOption(R.id.action_homeFragment_to_subsequentVisitsFragment)
                    "Delivery" -> navigateToOption(R.id.action_homeFragment_to_deliveryFragment)
                    "Post Natal Visits" -> navigateToOption(R.id.action_homeFragment_to_postNatalVisitFragment)
                    "Patients" -> navigateToOption(R.id.action_homeFragment_to_patientsFragment)
                }
            }


        })
    }

    private fun navigateToOption(destination: Int) {
        findNavController().navigate(destination)
        mViewModel.displaySelectedProductCategoryComplete()

    }

}
package com.polotechnologies.lindajamii.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.dataModels.HomeOption
import com.polotechnologies.lindajamii.databinding.FragmentHomeBinding

/**
 * A fragment used to offer option that can be offered to patients
 */
class HomeFragment : Fragment() {

    lateinit var mBinding:  FragmentHomeBinding
    lateinit var mViewModel :HomeFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)
        mViewModel = ViewModelProvider(this)[HomeFragmentViewModel::class.java]

        displayOptions()
        setObserver()
        return mBinding.root
    }

    private fun displayOptions() {
        mBinding.recyclerHome.adapter =
            HomeFragmentRecyclerAdapter(HOME_OPTIONS,
                HomeFragmentRecyclerAdapter.OnClickListener { productCategory ->
                    mViewModel.displaySelectedProductCategory(productCategory)
                })
    }

    private fun setObserver() {
        mViewModel.selectedHomeOption.observe(viewLifecycleOwner, Observer {homeOption->
            if(homeOption!=null){
                when(homeOption.option_title){
                    "Initial Visit"-> navigateToOption(R.id.action_homeFragment_to_initialVisitFragment)
                    "Subsequent Visits"-> navigateToOption(R.id.action_homeFragment_to_subsequentVisitsFragment)
                    "Delivery"-> navigateToOption(R.id.action_homeFragment_to_deliveryFragment)
                    "Post Natal Visits"-> navigateToOption(R.id.action_homeFragment_to_postNatalVisitFragment)
                    "Patients"->navigateToOption(R.id.action_homeFragment_to_patientsFragment)
                }
            }


        })
    }

    private fun navigateToOption(destination: Int) {
        findNavController().navigate(destination)
        mViewModel.displaySelectedProductCategoryComplete()

    }

    companion object{
        val HOME_OPTIONS = listOf<HomeOption>(
            HomeOption(R.drawable.linda_jamii_logo, "Initial Visit"),
            HomeOption(R.drawable.linda_jamii_logo, "Subsequent Visits"),
            HomeOption(R.drawable.linda_jamii_logo, "Delivery"),
            HomeOption(R.drawable.linda_jamii_logo, "Post Natal Visits"),
            HomeOption(R.drawable.linda_jamii_logo, "Patients")
        )
    }


}
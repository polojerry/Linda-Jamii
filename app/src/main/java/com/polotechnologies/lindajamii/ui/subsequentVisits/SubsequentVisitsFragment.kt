package com.polotechnologies.lindajamii.ui.subsequentVisits

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.datepicker.MaterialDatePicker
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.databinding.FragmentSubsequentVisitsBinding
import com.polotechnologies.lindajamii.network.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


/**
 *
 */
class SubsequentVisitsFragment : Fragment() {

    private lateinit var mBinding: FragmentSubsequentVisitsBinding
    private lateinit var mViewModel: SubsequentVisitViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_subsequent_visits, container, false)
        mBinding.buttonFinishSubsequentVisit.setOnClickListener { postSubsequentVisit() }

        val factory = SubsequentVisitViewModelFactory(requireActivity().application,mBinding)
        mViewModel = ViewModelProvider(this, factory)[SubsequentVisitViewModel::class.java]

        setDateListeners()

        return mBinding.root
    }

    private fun postSubsequentVisit() {
        if (!mViewModel.isFieldsValid()) return

        mBinding.buttonFinishSubsequentVisit.isEnabled = false

        mViewModel.writeStatusLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            when (isLoading) {
                true -> progressBarVisibility(View.VISIBLE)
                else -> progressBarVisibility(View.GONE)
            }

        })

        CoroutineScope(Dispatchers.Main).launch {
            mViewModel.saveSubsequentVisit().collect { resource ->

                when (resource) {
                    is Resource.Loading -> {
                        mViewModel.setIsLoading(true)
                    }


                    is Resource.Success -> {
                        mViewModel.setIsLoading(false)
                        requireActivity().onBackPressed()
                    }

                    is Resource.Failed -> {
                        mViewModel.setIsLoading(false)
                        toastMessage("Failed: ${resource.message}")
                    }
                }
            }
        }
    }

    private fun setDateListeners() {
        mBinding.textSubsequentVisitsDate.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                displayDatePicker("Today's Date")
            }
        }
        mBinding.textSubsequentVisitsNextVisit.setOnFocusChangeListener { _, hasFocus ->
            if(hasFocus){
                displayDatePicker("Next Visit")
            }

        }
    }

    private fun displayDatePicker(title: String) {
        val dateBuilder = MaterialDatePicker.Builder.datePicker()
        dateBuilder.setTitleText(title)

        val materialDatePicker = dateBuilder.build()
        materialDatePicker.show(requireActivity().supportFragmentManager, "DATE_PICKER")
        materialDatePicker.addOnPositiveButtonClickListener {date->
            when(title){
                "Today's Date"->{
                    mViewModel.dateOfVisit = date
                    mBinding.textSubsequentVisitsDate.setText(materialDatePicker.headerText)
                }

                "Next Visit"->{
                    mViewModel.nextVisit = date
                    mBinding.textSubsequentVisitsNextVisit.setText(materialDatePicker.headerText)
                }

            }

        }

    }

    private fun toastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun progressBarVisibility(visibility: Int) {
        mBinding.progressBarSubsequentVisit.visibility = visibility
    }

}
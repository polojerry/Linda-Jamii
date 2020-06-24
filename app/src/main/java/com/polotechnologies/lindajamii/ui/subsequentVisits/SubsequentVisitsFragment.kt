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
import com.polotechnologies.lindajamii.ui.initialvisit.medicalSurgicalHistory.MedicalSurgicalHistoryFragmentDirections


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

        val factory = SubsequentVisitViewModelFactory(mBinding)
        mViewModel = ViewModelProvider(this, factory)[SubsequentVisitViewModel::class.java]

        setObserver()
        mBinding.buttonFinishSubsequentVisit.setOnClickListener {
            if(mViewModel.isFieldsValid()){
                mBinding.progressBarSubsequentVisit.visibility = View.VISIBLE
                mBinding.buttonFinishSubsequentVisit.isEnabled = false
                mViewModel.saveMedicalSurgicalHistory()

            }
        }

        setClickListeners()

        return mBinding.root
    }

    private fun setClickListeners() {
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


    private fun setObserver() {
        mViewModel.exception.observe(viewLifecycleOwner, Observer { exception ->
            mBinding.progressBarSubsequentVisit.visibility = View.INVISIBLE
            if (exception == null) {
                Toast.makeText(requireContext().applicationContext, "Subsequent Visit Done", Toast.LENGTH_SHORT).show()
                requireActivity().onBackPressed()
            } else {
                Toast.makeText(
                    requireActivity().applicationContext,
                    "Failed: ${exception.localizedMessage}",
                    Toast.LENGTH_SHORT
                )
            }

        })
    }

}
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
import com.google.android.material.datepicker.MaterialDatePicker
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.databinding.FragmentMaternalProfileBinding
import com.polotechnologies.lindajamii.network.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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
        mBinding.buttonNextMedicalSurgicalHistory.setOnClickListener { postMaternalProfile() }


        val factory = MaternalProfileViewModelFactory(requireActivity().application, mBinding)
        mViewModel = ViewModelProvider(this, factory)[MaternalProfileViewModel::class.java]


        setDateListeners()

        return mBinding.root
    }

    private fun setDateListeners() {
        mBinding.textMaternalProfileLmp.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                displayDatePicker(getString(R.string.text_lmp))
            }
        }
        mBinding.textMaternalProfileEdd.setOnFocusChangeListener { _, hasFocus ->
            if(hasFocus){
                displayDatePicker(getString(R.string.text_edd))
            }

        }
    }

    private fun displayDatePicker(title: String) {
        val dateBuilder = MaterialDatePicker.Builder.datePicker()
        dateBuilder.setTitleText(title)

        val materialDatePicker = dateBuilder.build()
        materialDatePicker.show(requireActivity().supportFragmentManager, "DATE_PICKER")
        materialDatePicker.addOnPositiveButtonClickListener { _ ->
            when(title){
                getString(R.string.text_lmp)->{
                    mViewModel.lmp = materialDatePicker.headerText
                    mBinding.textMaternalProfileLmp.setText(materialDatePicker.headerText)
                }

                getString(R.string.text_edd)->{
                    mViewModel.edd = materialDatePicker.headerText
                    mBinding.textMaternalProfileEdd.setText(materialDatePicker.headerText)
                }

            }

        }

    }

    private fun postMaternalProfile() {
        if (!mViewModel.isFieldsValid()) return

        mBinding.buttonNextMedicalSurgicalHistory.isEnabled = false

        mViewModel.writeStatusLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            when (isLoading) {
                true -> progressBarVisibility(View.VISIBLE)
                else -> progressBarVisibility(View.GONE)
            }

        })

        CoroutineScope(Dispatchers.Main).launch {
            mViewModel.saveMaternalProfile().collect { resource ->

                when (resource) {
                    is Resource.Loading -> {
                        mViewModel.setIsLoading(true)
                    }


                    is Resource.Success -> {
                        mViewModel.setIsLoading(false)
                        val action =
                            MaternalProfileFragmentDirections.actionMaternalProfileFragmentToMedicalSurgicalHistoryFragment(
                                mViewModel.userId.value!!
                            )
                        findNavController().navigate(action)
                    }

                    is Resource.Failed -> {
                        mViewModel.setIsLoading(false)
                        toastMessage("Failed: ${resource.message}")
                    }
                }
            }
        }

    }


    private fun toastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun progressBarVisibility(visibility: Int) {
        mBinding.progressBarMaternalProfile.visibility = visibility
    }

}
package com.polotechnologies.lindajamii.ui.initialvisit.medicalSurgicalHistory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.databinding.FragmentMedicalSurgicalHistoryBinding
import com.polotechnologies.lindajamii.network.Resource
import com.polotechnologies.lindajamii.ui.initialvisit.maternalProfile.MaternalProfileFragmentDirections
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 *
 */
class MedicalSurgicalHistoryFragment : Fragment() {

    private lateinit var mBinding :FragmentMedicalSurgicalHistoryBinding
    private lateinit var mViewModel: MedicalSurgicalHistoryViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_medical_surgical_history, container, false)
        mBinding.buttonNextPhysicalAntenatalInfantFeeding.setOnClickListener { postMedicalSurgicalHistory() }

        val mUserId = MedicalSurgicalHistoryFragmentArgs.fromBundle(requireArguments()).userId
        val factory  = MedicalSurgicalHistoryViewModelFactory(requireActivity().application, mBinding, mUserId)
        mViewModel = ViewModelProvider(this, factory)[MedicalSurgicalHistoryViewModel::class.java]

        setFilledDropDownMenu()
        return mBinding.root
    }

    private fun postMedicalSurgicalHistory() {
        if (!mViewModel.isFieldsValid()) return

        mBinding.buttonNextPhysicalAntenatalInfantFeeding.isEnabled = false

        mViewModel.writeStatusLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            when (isLoading) {
                true -> progressBarVisibility(View.VISIBLE)
                else -> progressBarVisibility(View.GONE)
            }

        })

        CoroutineScope(Dispatchers.Main).launch {
            mViewModel.saveMedicalSurgicalHistory().collect { resource ->

                when (resource) {
                    is Resource.Loading -> {
                        mViewModel.setIsLoading(true)
                    }


                    is Resource.Success -> {
                        mViewModel.setIsLoading(false)
                        val action =
                            MedicalSurgicalHistoryFragmentDirections.actionMedicalSurgicalHistoryFragmentToPhysicalAntenatalFeeding(
                                mViewModel.mUserId
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

    private fun setFilledDropDownMenu() {
        val yesNoDropDown = resources.getStringArray(R.array.filled_drop_down_yes_no)
        val categoryAdapter  = ArrayAdapter<String>(
            requireContext().applicationContext,
            R.layout.layout_dropdown_menu_popup,
            yesNoDropDown
        )

        mBinding.textMedicalSurgicalHistoryDiabetes.setAdapter(categoryAdapter)
        mBinding.textMedicalSurgicalHistoryHypertension.setAdapter(categoryAdapter)
        mBinding.textMedicalSurgicalHistoryBloodTransfusion.setAdapter(categoryAdapter)
        mBinding.textMedicalSurgicalHistoryTuberculosis.setAdapter(categoryAdapter)
        mBinding.textMedicalSurgicalHistoryTwins.setAdapter(categoryAdapter)
        mBinding.textMedicalSurgicalHistoryFamilyHistoryTuberculosis.setAdapter(categoryAdapter)
    }

    private fun toastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun progressBarVisibility(visibility: Int) {
        mBinding.progressBarMedicalSurgicalHistory.visibility = visibility
    }

}
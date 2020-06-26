package com.polotechnologies.lindajamii.ui.initialvisit.physicalAntenatalFeedingProfile

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
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.databinding.FragmentPhysicalAntenatalFeedingBinding
import com.polotechnologies.lindajamii.network.Resource
import com.polotechnologies.lindajamii.ui.initialvisit.medicalSurgicalHistory.MedicalSurgicalHistoryFragmentDirections
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 *
 */
class PhysicalAntenatalFeeding : Fragment() {

    private lateinit var mBinding: FragmentPhysicalAntenatalFeedingBinding
    private lateinit var mViewModel: PhysicalAntenatalFeedingViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_physical_antenatal_feeding,
            container,
            false
        )
        mBinding.buttonFinishInitial.setOnClickListener { postPhysicalAntenatalFeeding() }

        val userId = PhysicalAntenatalFeedingArgs.fromBundle(requireArguments()).userId
        val factory = PhysicalAntenatalFeedingViewModelFactory(
            requireActivity().application,
            mBinding,
            userId
        )
        mViewModel = ViewModelProvider(this, factory)[PhysicalAntenatalFeedingViewModel::class.java]


        setFilledDropDownMenu()
        return mBinding.root
    }

    private fun postPhysicalAntenatalFeeding() {
        if (!mViewModel.isFieldsValid()) return

        mBinding.buttonFinishInitial.isEnabled = false

        mViewModel.writeStatusLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            when (isLoading) {
                true -> progressBarVisibility(View.VISIBLE)
                else -> progressBarVisibility(View.GONE)
            }

        })

        CoroutineScope(Dispatchers.Main).launch {
            mViewModel.savePhysicalAntenatalFeeding().collect { resource ->

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

    private fun setFilledDropDownMenu() {
        val yesNoDropDown = resources.getStringArray(R.array.filled_drop_down_yes_no)
        val hivDropDown = resources.getStringArray(R.array.filled_drop_down_hiv)

        val categoryAdapterYesNo = ArrayAdapter<String>(
            requireContext().applicationContext,
            R.layout.layout_dropdown_menu_popup,
            yesNoDropDown
        )

        val categoryAdapterHiv = ArrayAdapter<String>(
            requireContext().applicationContext,
            R.layout.layout_dropdown_menu_popup,
            hivDropDown
        )

        mBinding.textInfantFeedingCounselling.setAdapter(categoryAdapterYesNo)
        mBinding.textInfantFeedingCounselingBreastfeeding.setAdapter(categoryAdapterYesNo)
        mBinding.textAntenatalProfileHiv.setAdapter(categoryAdapterHiv)

    }

    private fun toastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun progressBarVisibility(visibility: Int) {
        mBinding.progressBarPhysicalAntenatalInfantFeeding.visibility = visibility
    }

}
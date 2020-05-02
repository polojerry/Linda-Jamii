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
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.databinding.FragmentPhysicalAntenatalFeedingBinding
import com.polotechnologies.lindajamii.ui.initialvisit.medicalSurgicalHistory.MedicalSurgicalHistoryFragmentDirections
import com.polotechnologies.lindajamii.ui.initialvisit.medicalSurgicalHistory.MedicalSurgicalHistoryViewModel
import com.polotechnologies.lindajamii.ui.initialvisit.medicalSurgicalHistory.MedicalSurgicalHistoryViewModelFactory

/**
 *
 */
class PhysicalAntenatalFeeding : Fragment() {

    private lateinit var mBinding: FragmentPhysicalAntenatalFeedingBinding
    private lateinit var mViewModel: PhysicalAntenatalFeedingViewModel
    private  lateinit var mDatabase: FirebaseFirestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_physical_antenatal_feeding, container, false)
        mDatabase = FirebaseFirestore.getInstance()

        val userId = PhysicalAntenatalFeedingArgs.fromBundle(requireArguments()).userId
        val factory  = PhysicalAntenatalFeedingViewModelFactory(mDatabase, mBinding, userId)
        mViewModel = ViewModelProvider(this, factory)[PhysicalAntenatalFeedingViewModel::class.java]
        mViewModel._userId.value= userId

        setObserver()
        mBinding.buttonFinishInitial.setOnClickListener {
            if(mViewModel.isFieldsValid()){
                mBinding.progressBarPhysicalAntenatalInfanctFeeding.visibility = View.VISIBLE
                mBinding.buttonFinishInitial.isEnabled = false
                mViewModel.savePhysicalAntenatalFeeding()

            }
        }

        setFilledDropDownMenu()
        return mBinding.root
    }
    private fun setObserver() {
        mViewModel.writeStatus.observe(viewLifecycleOwner, Observer { status ->
            mBinding.progressBarPhysicalAntenatalInfanctFeeding.visibility = View.INVISIBLE
            if (status == true) {
                Toast.makeText(context!!.applicationContext, "Initial Visit Done", Toast.LENGTH_SHORT).show()
                activity!!.onBackPressed()
            } else {
                Toast.makeText(
                    context!!.applicationContext,
                    "Failed: ${mViewModel.exception.value!!.localizedMessage}",
                    Toast.LENGTH_SHORT
                )
            }

        })
    }

    private fun setFilledDropDownMenu() {
        val yes_no_drop_down = resources.getStringArray(R.array.filled_drop_down_yes_no)
        val hiv_drop_down = resources.getStringArray(R.array.filled_drop_down_hiv)

        val categoryAdapterYesNo  = ArrayAdapter<String>(
            context!!.applicationContext,
            R.layout.layout_dropdown_menu_popup,
            yes_no_drop_down
        )

        val categoryAdapterHiv  = ArrayAdapter<String>(
            context!!.applicationContext,
            R.layout.layout_dropdown_menu_popup,
            hiv_drop_down
        )

        mBinding.textInfantFeedingCouseling.setAdapter(categoryAdapterYesNo)
        mBinding.textInfantFeedingCouselingBreastfeeding.setAdapter(categoryAdapterYesNo)
        mBinding.textAntenatalProfileHiv.setAdapter(categoryAdapterHiv)

    }

}
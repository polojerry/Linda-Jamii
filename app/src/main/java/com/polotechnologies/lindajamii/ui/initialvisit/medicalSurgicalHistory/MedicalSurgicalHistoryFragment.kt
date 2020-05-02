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
import com.google.firebase.firestore.FirebaseFirestore
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.databinding.FragmentMedicalSurgicalHistoryBinding
import com.polotechnologies.lindajamii.ui.initialvisit.maternalProfile.MaternalProfileFragmentDirections
import com.polotechnologies.lindajamii.ui.initialvisit.maternalProfile.MaternalProfileViewModel
import com.polotechnologies.lindajamii.ui.initialvisit.maternalProfile.MaternalProfileViewModelFactory

/**
 *
 */
class MedicalSurgicalHistoryFragment : Fragment() {

    private lateinit var mBinding :FragmentMedicalSurgicalHistoryBinding
    private lateinit var mViewModel: MedicalSurgicalHistoryViewModel
    private lateinit var mDatabase: FirebaseFirestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_medical_surgical_history, container, false)
        mDatabase = FirebaseFirestore.getInstance()

        val mUserId = MedicalSurgicalHistoryFragmentArgs.fromBundle(requireArguments()).userId

        val factory  = MedicalSurgicalHistoryViewModelFactory(mDatabase, mBinding, mUserId)
        mViewModel = ViewModelProvider(this, factory)[MedicalSurgicalHistoryViewModel::class.java]
        mViewModel._userId.value = mUserId

        setObserver()
        mBinding.buttonNextPhysicalAntenatalInfantFeeding.setOnClickListener {
            if(mViewModel.isFieldsValid()){
                mBinding.progressBarMedicalSurgicalHistory.visibility = View.VISIBLE
                mBinding.buttonNextPhysicalAntenatalInfantFeeding.isEnabled = false
                mViewModel.saveMedicalSurgicalHistory()

            }
        }

        setFilledDropDownMenu()
        return mBinding.root
    }

    private fun setObserver() {
        mViewModel.writeStatus.observe(viewLifecycleOwner, Observer { status ->
            mBinding.progressBarMedicalSurgicalHistory.visibility = View.INVISIBLE
            if (status == true) {
                val action =
                    MedicalSurgicalHistoryFragmentDirections.actionMedicalSurgicalHistoryFragmentToPhysicalAntenatalFeeding(
                        mViewModel.mUserId
                    )
                findNavController().navigate(action)
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
        val categoryAdapter  = ArrayAdapter<String>(
            context!!.applicationContext,
            R.layout.layout_dropdown_menu_popup,
            yes_no_drop_down
        )

        mBinding.textMedicalSurgicalHistoryDiabetes.setAdapter(categoryAdapter)
        mBinding.textMedicalSurgicalHistoryHypertension.setAdapter(categoryAdapter)
        mBinding.textMedicalSurgicalHistoryBloodTransfusion.setAdapter(categoryAdapter)
        mBinding.textMedicalSurgicalHistoryTuberculosis.setAdapter(categoryAdapter)
        mBinding.textMedicalSurgicalHistoryTwins.setAdapter(categoryAdapter)
        mBinding.textMedicalSurgicalHistoryFamilyHistoryTuberculosis.setAdapter(categoryAdapter)
    }

}
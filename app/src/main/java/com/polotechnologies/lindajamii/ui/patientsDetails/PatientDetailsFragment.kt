package com.polotechnologies.lindajamii.ui.patientsDetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails
import com.polotechnologies.lindajamii.database.LindaJamiiDatabase.Companion.getDatabase
import com.polotechnologies.lindajamii.databinding.FragmentPatientsDetailsBinding

class PatientDetailsFragment : Fragment(), Toolbar.OnMenuItemClickListener {

    private lateinit var mBinding: FragmentPatientsDetailsBinding
    private lateinit var mViewModel: PatientDetailsViewModel
    private lateinit var mExpectantDetails: ExpectantDetails
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_patients_details, container, false)

        val dataSource = getDatabase(requireNotNull(context)).expectantDetailsDao

        val ancNumber = PatientDetailsFragmentArgs.fromBundle(requireArguments()).ancNumber
        val factory = PatientDetailsViewModelFactory(ancNumber, dataSource)

        mViewModel = ViewModelProvider(this, factory)[PatientDetailsViewModel::class.java]
        mViewModel.patientDetails.observe(viewLifecycleOwner, Observer {expectantDetails->
            mExpectantDetails = expectantDetails
            mBinding.expectantDetails = expectantDetails
        })



        setClickListeners()

        return mBinding.root
    }

    private fun setClickListeners() {
        mBinding.toolbarPatientsDetails.setOnMenuItemClickListener(this)
        mBinding.toolbarPatientsDetails.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }


    override fun onMenuItemClick(item: MenuItem?): Boolean {

        when (item!!.itemId) {
            R.id.action_call_patient -> callPatient()
            R.id.action_remind_later -> remindLater()
        }

        return true
    }

    private fun remindLater() {
        Toast.makeText(context, "Remind Later.....", Toast.LENGTH_SHORT).show()
    }

    private fun callPatient() {
        val phoneNumber = mExpectantDetails.maternalProfile?.telephone
        val phoneIntent = Intent(Intent.ACTION_CALL)
        phoneIntent.data = Uri.parse("tel:${phoneNumber}")
        startActivity(phoneIntent)
    }
}
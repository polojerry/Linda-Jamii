package com.polotechnologies.lindajamii.ui.patientsDetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails
import com.polotechnologies.lindajamii.dataModels.Patients
import com.polotechnologies.lindajamii.databinding.FragmentPatientsDetailsBinding

class PatientsDetailsFragment : Fragment() {

    private lateinit var mBinding: FragmentPatientsDetailsBinding
    private lateinit var patient: ExpectantDetails
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_patients_details, container, false)


        patient = PatientsDetailsFragmentArgs.fromBundle(requireArguments()).expectantPatient
        /*displayPatientDetails()

        mBinding.buttonPatientDetailsCall.setOnClickListener {
            callPatient()
        }*/
        return mBinding.root
    }

    /*private fun displayPatientDetails() {
        mBinding.textPatientName.setText(patient.patientName)
        mBinding.textPatientPhoneNumber.setText(patient.patientsPhoneNumber)
        mBinding.textPatientPreviousVisit.setText(patient.patientsPrevious_Visit)
        mBinding.textPatientNextVisit.setText(patient.patientsNextVisit)
        mBinding.textPatientMaritalStatus.setText(patient.patientsMaritalStatus)

    }

    private fun callPatient() {
        val callIntent = Intent(Intent.ACTION_CALL, Uri.fromParts("tel", patient.patientsPhoneNumber,null))
        startActivity(callIntent)
    }
*/

}
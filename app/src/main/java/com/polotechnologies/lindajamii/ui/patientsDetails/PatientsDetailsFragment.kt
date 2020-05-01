package com.polotechnologies.lindajamii.ui.patientsDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.dataModels.Patients
import com.polotechnologies.lindajamii.databinding.FragmentPatientsDetailsBinding
import com.polotechnologies.lindajamii.utils.callPatient

internal class PatientsDetailsFragment : Fragment(R.layout.fragment_patients_details) {

    private lateinit var mBinding: FragmentPatientsDetailsBinding
    private lateinit var patient: Patients

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding = FragmentPatientsDetailsBinding.bind(view)
        patient = PatientsDetailsFragmentArgs.fromBundle(requireArguments()).patient
        displayPatientDetails()

        mBinding.buttonPatientDetailsCall.setOnClickListener {
            callPatient(patient)
        }
    }

    private fun displayPatientDetails() {
        mBinding.textPatientName.setText(patient.patientName)
        mBinding.textPatientPhoneNumber.setText(patient.patientsPhoneNumber)
        mBinding.textPatientPreviousVisit.setText(patient.patientsPrevious_Visit)
        mBinding.textPatientNextVisit.setText(patient.patientsNextVisit)
        mBinding.textPatientMaritalStatus.setText(patient.patientsMaritalStatus)
    }
}
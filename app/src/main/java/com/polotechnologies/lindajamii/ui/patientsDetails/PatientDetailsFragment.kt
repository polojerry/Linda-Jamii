package com.polotechnologies.lindajamii.ui.patientsDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails
import com.polotechnologies.lindajamii.databinding.FragmentPatientsDetailsBinding
import com.polotechnologies.lindajamii.util.DateConverter
import com.polotechnologies.lindajamii.util.ExpectantVisitNotification

class PatientDetailsFragment : Fragment() {

    private lateinit var mBinding: FragmentPatientsDetailsBinding
    private lateinit var mViewModelFactory: PatientDetailsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_patients_details, container, false)


        val ancNumber = PatientDetailsFragmentArgs.fromBundle(requireArguments()).ancNumber
        val factory = PatientDetailsViewModelFactory(activity!!.application, ancNumber)
        mViewModelFactory = ViewModelProvider(this, factory)[PatientDetailsViewModel::class.java]


        setObservers()
        return mBinding.root
    }

    private fun setObservers() {
        mViewModelFactory.patientDetails?.observe(viewLifecycleOwner, Observer {details->
            Toast.makeText(context, "PatientName: ${details.maternalProfile!!.nameOfClient}", Toast.LENGTH_SHORT).show()
            ExpectantVisitNotification.notify(context!!,"Expectant Visit", details.maternalProfile.nameOfClient, DateConverter.getStringDate(details.nextVisit!!))
        })


    }

   /* private fun callPatient() {
        val callIntent = Intent(Intent.ACTION_CALL, Uri.fromParts("tel", patient.patientsPhoneNumber,null))
        startActivity(callIntent)
    }
*/
}
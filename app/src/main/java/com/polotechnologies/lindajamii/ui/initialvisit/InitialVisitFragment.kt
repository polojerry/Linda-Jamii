package com.polotechnologies.lindajamii.ui.initialvisit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.dataModels.PatientDetails
import com.polotechnologies.lindajamii.database.LindaJamiiDatabase
import com.polotechnologies.lindajamii.databinding.FragmentInitialVisitBinding
import kotlinx.android.synthetic.main.first_visit_maternal_profile.*
import kotlinx.android.synthetic.main.first_visit_medical_surgical_history.*
import kotlinx.android.synthetic.main.first_visit_physical_examination.*
import kotlinx.android.synthetic.main.fragment_initial_visit.*

/**
 * A fragment used to insert data of initial visit by the expectant mother
 */
class InitialVisitFragment : Fragment() {

    private lateinit var mBinding: FragmentInitialVisitBinding
    private lateinit var mViewModel: InitialVisitViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_initial_visit, container, false )

        val application = requireNotNull(this.activity).application
        val dataSource = LindaJamiiDatabase.getInstance(application).patientProfileDAO
        val viewModelFactory = InitialVisitViewModelFactory(application,dataSource)

        mViewModel = ViewModelProvider(this,viewModelFactory).get(InitialVisitViewModel::class.java)

        setClickListener()
        setObervers()
        return mBinding.root
    }

    private fun setObervers() {
        mViewModel.insertedDetailId.observe(viewLifecycleOwner, Observer {id->
            if(id>0){
                Toast.makeText(context, "Profile saved with Number + $id", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setClickListener() {
       /* button_save_maternal_profile.setOnClickListener {
            val nameOfInstitution = text_maternal_profile_name_of_institution.text.toString()
            val mflNumber = text_maternal_profile_mf_number.text.toString().toInt()
            val ancNumber = text_maternal_profile_anc_number.text.toString().toInt()
            val nameOfClient = text_maternal_profile_name_of_client.text.toString()
            val age = text_maternal_profile_age.text.toString().toInt()
            val gravida = text_maternal_profile_parity.text.toString()
            val height = text_maternal_profile_height.text.toString().toDouble()
            val weight = text_maternal_profile_weight.text.toString().toDouble()
            *//*val lmp = text_maternal_profile_lmp.text.toString()
            val edd = text_maternal_profile_edd.text.toString()*//*
            val maritalStatus =text_maternal_profile_marital_status.text.toString()
            val education = text_maternal_profile_education.text.toString()
            val address =  text_maternal_profile_address.text.toString()
            val mobileNumber = text_maternal_profile_telephone.toString()
            val nextOfKin = text_maternal_profile_next_of_kin.text.toString()
            val nextOfKinRelation = text_maternal_profile_relationship.text.toString()
            val nextOfKinContact = text_layout_profile_contact_phone.text.toString()

            val maternalProfile = PatientDetails.MaternalProfile(
                nameOfInstitution,mflNumber,ancNumber,nameOfClient,age,gravida,height,weight,maritalStatus,
                education,address,mobileNumber,nextOfKin,nextOfKinRelation,nextOfKinContact
            )

            mViewModel.saveMaternalProfile(maternalProfile)
            Toast.makeText(context, "Maternal Profile Saved", Toast.LENGTH_SHORT).show()

        }
        *//*
        * to be implemented
        *
        * *//*
        button_save_medical_surgical_history.setOnClickListener {
            Toast.makeText(context, "Medical Surgical History Saved", Toast.LENGTH_SHORT).show()
        }
        button_save_physical_examination.setOnClickListener {
            Toast.makeText(context, "Physical Examination Saved", Toast.LENGTH_SHORT).show()
        }

        button_finish_initial_visit.setOnClickListener {
            mViewModel.savePatientProfile()
        }*/
    }

}
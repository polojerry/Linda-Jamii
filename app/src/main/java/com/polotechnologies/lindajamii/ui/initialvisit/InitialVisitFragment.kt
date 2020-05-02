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
 * A fragment used host initial visit by expectant mother fragment
 */
class InitialVisitFragment : Fragment() {

    private lateinit var mBinding: FragmentInitialVisitBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_initial_visit, container, false )

        return mBinding.root
    }




}
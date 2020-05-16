package com.polotechnologies.lindajamii.ui.patientsAvailable

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentPagerAdapter
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.databinding.FragmentPatientsBinding


/**

 */
class PatientsHostFragment : Fragment() {

    private lateinit var mBinding: FragmentPatientsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_patients_host, container, false)


        return mBinding.root
    }

}
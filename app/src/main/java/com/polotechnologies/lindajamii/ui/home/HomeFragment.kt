package com.polotechnologies.lindajamii.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.databinding.FragmentHomeBinding

/**
 * A fragment used to offer option that can be offered to patients
 */
class HomeFragment : Fragment() {

    lateinit var mBinding:  FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)
        
        return mBinding.root
    }

}
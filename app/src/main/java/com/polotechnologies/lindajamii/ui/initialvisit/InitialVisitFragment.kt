package com.polotechnologies.lindajamii.ui.initialvisit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.databinding.FragmentInitialVisitBinding


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
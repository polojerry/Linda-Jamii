package com.polotechnologies.lindajamii.ui.subsequentVisits

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.databinding.FragmentSubsequentVisitsBinding


/**
 *
 */
class SubsequentVisitsFragment : Fragment() {

    private lateinit var mBinding: FragmentSubsequentVisitsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_subsequent_visits, container, false)
        return mBinding.root
    }

}
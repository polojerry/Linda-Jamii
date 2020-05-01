package com.polotechnologies.lindajamii.ui.subsequentVisits

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.databinding.FragmentSubsequentVisitsBinding

/**
 *
 */

internal class SubsequentVisitsFragment : Fragment(R.layout.fragment_subsequent_visits) {

    private lateinit var mBinding: FragmentSubsequentVisitsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding = FragmentSubsequentVisitsBinding.bind(view)
    }
}
package com.polotechnologies.lindajamii.ui.delivery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.databinding.FragmentDeliveryBinding

/**
 */

internal class DeliveryFragment : Fragment(R.layout.fragment_delivery) {

    private lateinit var mBinding: FragmentDeliveryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentDeliveryBinding.bind(view)

    }
}
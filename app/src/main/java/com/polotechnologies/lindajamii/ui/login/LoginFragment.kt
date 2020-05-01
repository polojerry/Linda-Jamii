package com.polotechnologies.lindajamii.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.databinding.FragmentLoginBinding
import com.polotechnologies.lindajamii.utils.navigateToHomeScreen


/**
 * A fragment used to authenticate only registered health worker from each facility
 */
internal class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var mBinding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding = FragmentLoginBinding.bind(view)

        mBinding.buttonLogin.setOnClickListener {
            navigateToHomeScreen()
        }
    }
}
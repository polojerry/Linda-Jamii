package com.polotechnologies.lindajamii.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.databinding.FragmentLoginBinding


/**
 * A fragment used to authenticate only registered health worker from each facility
 */
class LoginFragment : Fragment() {

    private lateinit var mBinding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_login, container, false)

        mBinding.buttonLogin.setOnClickListener {
            navigateToHomeScreen()
        }
        return mBinding.root
    }

    private fun navigateToHomeScreen() {
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
    }
}
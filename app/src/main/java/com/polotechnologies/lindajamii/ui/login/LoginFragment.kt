package com.polotechnologies.lindajamii.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.databinding.FragmentLoginBinding


/**
 * A fragment used to authenticate only registered health worker from each facility
 */
class LoginFragment : Fragment() {

    private lateinit var mBinding: FragmentLoginBinding
    private lateinit var mAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        mAuth = FirebaseAuth.getInstance()

        mBinding.buttonLogin.setOnClickListener { loginUser() }

        return mBinding.root
    }

    private fun loginUser() {
        val userName = mBinding.textLoginEmail.text.toString()
        val userPass = mBinding.textLoginPassword.text.toString()

        mAuth.signInWithEmailAndPassword(userName, userPass).addOnSuccessListener {
            Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
            navigateToHomeScreen()
        }.addOnFailureListener { exception ->
            Toast.makeText(requireContext(), "Login Failed: ${exception.localizedMessage}", Toast.LENGTH_SHORT).show()

        }

    }

    private fun navigateToHomeScreen() {
        findNavController().navigate(R.id.homeFragment)
    }
}
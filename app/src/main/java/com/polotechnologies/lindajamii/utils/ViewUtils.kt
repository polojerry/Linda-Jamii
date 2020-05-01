package com.polotechnologies.lindajamii.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.polotechnologies.lindajamii.R
import com.polotechnologies.lindajamii.dataModels.Patients

fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).apply {
        show()
    }
}

fun Fragment.navigateToHomeScreen() {
    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
}

fun Fragment.callPatient( patient: Patients) {
    val callIntent = Intent(Intent.ACTION_CALL, Uri.fromParts("tel", patient.patientsPhoneNumber,null))
    startActivity(callIntent)
}
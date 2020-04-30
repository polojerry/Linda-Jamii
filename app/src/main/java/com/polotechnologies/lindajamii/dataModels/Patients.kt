package com.polotechnologies.lindajamii.dataModels

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Patients (
    val patientName : String,
    val patientsPhoneNumber: String,
    val patientsNextVisit : String,
    val patientsPrevious_Visit : String,
    val patientsMaritalStatus :String
) : Parcelable
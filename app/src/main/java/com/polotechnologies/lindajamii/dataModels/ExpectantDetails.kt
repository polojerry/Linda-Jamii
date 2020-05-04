package com.polotechnologies.lindajamii.dataModels

import android.os.Parcelable
import com.google.firebase.firestore.ServerTimestamp
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class ExpectantDetails(
    val patientId :String = "",
    @ServerTimestamp val timeStamp : Date? = null,
    val maternalProfile: ExpectantMaternalProfile? = null,
    val medicalSurgicalHistory : ExpectantMedicalSurgicalHistory? = null,
    val physicalAntenatalFeeding : ExpectantPhysicalAntenatalFeeding? = null
) : Parcelable {
    @Parcelize
    data class ExpectantMaternalProfile(
        val nameOfInstitution: String = "",
        val mflNumber: String = "",
        val ancNumber: String = "",
        val pncNumber: String = "",
        val nameOfClient: String = "",
        val ageOfClient: String = "",
        val gravida: String = "",
        val parity: String = "",
        val height: String = "",
        val weight: String = "",
        val lmp: String = "",
        val edd: String = "",
        val maritalStatus: String = "",
        val education: String = "",
        val address: String = "",
        val telephone: String = "",
        val nextOfKin: String = "",
        val relationShip: String = "",
        val nextOfKinContact: String = ""
    ) : Parcelable

    @Parcelize
    data class ExpectantMedicalSurgicalHistory (
        val surgicalOperation: String = "",
        val diabetes : String= "",
        val hypertension : String= "",
        val bloodTransfusion : String= "",
        val tuberculosis : String= "",
        val specificDrugAllergy: String = "",
        val otherDrugAllergies: String = "",
        val familyHistoryTwins : String= "",
        val familyHistoryTuberculosis : String= ""

    ) : Parcelable

    @Parcelize
    data class ExpectantPhysicalAntenatalFeeding(
        val general : String= "",
                val bp : String= "",
                val height : String= "",
                val cvs : String= "",
                val resp : String= "",
                val breasts: String = "",
                val abdomen : String= "",
                val vaginalExamination: String = "",
                val dischargeGenitalUlcers: String = "",
                val hb : String= "",
                val bloodGroup : String= "",
                val rhesus : String= "",
                val serology: String = "",
                val tbScreening : String= "",
                val dateIPTIsonaziadGiven: String = "",
                val nextVisit : String= "",
                val hiv : String= "",
                val urianalysis: String = "",
                val givenHIVCounsellingAndTest : String= "",
                val feedingCounsellingDone : String= "",
                val counselingOnExclusiveBreastfeedingDone: String = ""

    ) : Parcelable
}
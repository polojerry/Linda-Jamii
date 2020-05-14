package com.polotechnologies.lindajamii.dataModels

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.firebase.firestore.ServerTimestamp
import com.polotechnologies.lindajamii.database.typeConveters.TypeConverterDate
import com.polotechnologies.lindajamii.database.typeConveters.TypeConverterExpectantMaternalProfile
import com.polotechnologies.lindajamii.database.typeConveters.TypeConverterExpectantMedicalSurgicalHistory
import com.polotechnologies.lindajamii.database.typeConveters.TypeConverterExpectantPhysicalAntenatalFeeding
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "expectant_details_table")
data class ExpectantDetails(
    @PrimaryKey @ColumnInfo(name = "user_anc_number")
    val patientId :String = "",

    @ColumnInfo(name = "time_stamp")
    @TypeConverters(TypeConverterDate::class)
    @ServerTimestamp val timeStamp : Date? = null,

    @ColumnInfo(name = "maternal_profile")
    @TypeConverters(TypeConverterExpectantMaternalProfile::class)
    val maternalProfile: ExpectantMaternalProfile? = null,

    @ColumnInfo(name = "medical_surgical_history")
    @TypeConverters(TypeConverterExpectantMedicalSurgicalHistory::class)
    val medicalSurgicalHistory : ExpectantMedicalSurgicalHistory? = null,

    @ColumnInfo(name = "physical_antenatal_feeding")
    @TypeConverters(TypeConverterExpectantPhysicalAntenatalFeeding::class)
    val physicalAntenatalFeeding : ExpectantPhysicalAntenatalFeeding? = null,

    @ColumnInfo(name = "next_visit")
    val nextVisit: Long?=null
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

fun List<ExpectantDetails>.asDomainModel(): List<ExpectantDetails> {
    return map {expectantPatient->
        ExpectantDetails(
            patientId = expectantPatient.patientId,
            timeStamp = expectantPatient.timeStamp,
            maternalProfile = expectantPatient.maternalProfile,
            medicalSurgicalHistory = expectantPatient.medicalSurgicalHistory,
            physicalAntenatalFeeding = expectantPatient.physicalAntenatalFeeding)
    }
}
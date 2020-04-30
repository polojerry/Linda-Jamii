package com.polotechnologies.lindajamii.dataModels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.polotechnologies.lindajamii.dataModels.typeConveters.AntenatalProfileConverter
import com.polotechnologies.lindajamii.dataModels.typeConveters.MaternalProfileConverter
import com.polotechnologies.lindajamii.dataModels.typeConveters.MedicalSurgicalHistoryConverter
import com.polotechnologies.lindajamii.dataModels.typeConveters.PhysicalExaminationConverter

@Entity(tableName = "patients_details_table")
data class PatientDetails(

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "patients_number")
    val patientNumber: Long,

    @ColumnInfo(name = "maternal_profile")
    @TypeConverters(MaternalProfileConverter::class)
    val maternalProfile: MaternalProfile?,

    @ColumnInfo(name = "medical_surgical_history")
    @TypeConverters(MedicalSurgicalHistoryConverter::class)
    val medicalSurgicalHistory: MedicalSurgicalHistory?,

    @ColumnInfo(name = "physical_examination")
    @TypeConverters(PhysicalExaminationConverter::class)
    val physicalExamination: PhysicalExamination?,

    @ColumnInfo(name = "antenatal_profile")
    @TypeConverters(AntenatalProfileConverter::class)
    val antenatalProfile: AntenatalProfile?
) {
    data class MaternalProfile(
        val institutionName: String,
        val mfl_number: Int,
        val anc_number: Int,
        val name_of_client: String,
        val age_of_client: Int,
        val gravida: String,
        val height: Double,
        val weight: Double,
        val marital_status: String,
        val education: String,
        val address: String,
        val telephone: String,
        val next_of_kin: String,
        val relationship: String,
        val next_of_kin_contact: String

    )

    data class MedicalSurgicalHistory(
        val surgicalOperation: String,
        val diabetes: String,
        val hypertension: String,
        val bloodTransfusion: String,
        val tuberculosis: String,
        val allergic_drug_specific: String,
        val allergic_drug_others_specific: String,
        val family_history_twins: String,
        val family_history_tuberculosis: String
    )

    data class PhysicalExamination(
        val general: String,
        val bp: String,
        val height: String,
        val cvs: String,
        val resp: String,
        val breast: String,
        val abdomen: String,
        val vaginalExamination: String,
        val dischrge_genital_ulcer: String
    )

    data class AntenatalProfile(
        val hB: String,
        val blood_group: String,
        val rhesus_factor: String,
        val serology: String,
        val tb_screening_intense: String,
        val hiv: String,
        val urinalisys: String,
        val hiv_couseling_done: String

    )
}
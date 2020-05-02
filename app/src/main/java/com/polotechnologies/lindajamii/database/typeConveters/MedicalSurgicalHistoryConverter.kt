package com.polotechnologies.lindajamii.database.typeConveters

import androidx.room.TypeConverter
import com.polotechnologies.lindajamii.dataModels.PatientDetails.MedicalSurgicalHistory

class MedicalSurgicalHistoryConverter {
    @TypeConverter
    fun toString(medicalSurgicalHistory: MedicalSurgicalHistory?): String {
        var history = ""

        if (medicalSurgicalHistory == null) {
            return history
        } else {
            history = "${medicalSurgicalHistory.surgicalOperation}," +
                    "${medicalSurgicalHistory.diabetes}," +
                    "${medicalSurgicalHistory.hypertension}," +
                    "${medicalSurgicalHistory.bloodTransfusion}," +
                    "${medicalSurgicalHistory.tuberculosis}," +
                    "${medicalSurgicalHistory.allergic_drug_specific}," +
                    "${medicalSurgicalHistory.allergic_drug_others_specific}," +
                    "${medicalSurgicalHistory.family_history_twins}," +
                    medicalSurgicalHistory.family_history_tuberculosis
        }


        return history

    }

    @TypeConverter
    fun toMedicalSurgicalHistory(profile: String?): MedicalSurgicalHistory? {

        var medicalSurgicalHistory: MedicalSurgicalHistory? = MedicalSurgicalHistory(
            "", "", "", "", "", "",
            "", "",""
        )

        if (profile.equals("")) {

            return medicalSurgicalHistory

        } else {

            val stats: List<String> = profile!!.split(",")

            medicalSurgicalHistory = MedicalSurgicalHistory(
                stats[0],
                stats[1],
                stats[2],
                stats[3],
                stats[4],
                stats[5],
                stats[6],
                stats[7],
                stats[8]
            )

            return medicalSurgicalHistory
        }

    }
}
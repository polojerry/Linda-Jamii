package com.polotechnologies.lindajamii.database.typeConveters

import androidx.room.TypeConverter
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails.*

class TypeConverterExpectantMedicalSurgicalHistory {
    @TypeConverter
    fun toString(expectantMedicalSurgicalHistory: ExpectantMedicalSurgicalHistory?): String {
        var medicalSurgicalHistory = ""

        if (expectantMedicalSurgicalHistory == null) {
            return medicalSurgicalHistory
        } else {
            medicalSurgicalHistory = "${expectantMedicalSurgicalHistory.surgicalOperation}=" +
                    "${expectantMedicalSurgicalHistory.diabetes}=" +
                    "${expectantMedicalSurgicalHistory.hypertension}=" +
                    "${expectantMedicalSurgicalHistory.bloodTransfusion}="+
                    "${expectantMedicalSurgicalHistory.tuberculosis}=" +
                    "${expectantMedicalSurgicalHistory.specificDrugAllergy}="+
                    "${expectantMedicalSurgicalHistory.otherDrugAllergies}=" +
                    "${expectantMedicalSurgicalHistory.familyHistoryTwins}=" +
                    expectantMedicalSurgicalHistory.familyHistoryTuberculosis
        }
        return medicalSurgicalHistory

    }

    @TypeConverter
    fun toExpectantMedicalSurgicalHistory(medicalSurgicalHistory: String?): ExpectantMedicalSurgicalHistory? {

        var expectantSurgicalHistory: ExpectantMedicalSurgicalHistory? =
            ExpectantMedicalSurgicalHistory(
                "", "", "", "", "", "",
                "", "", ""
            )

        if (medicalSurgicalHistory.equals("")) {

            return expectantSurgicalHistory

        } else {

            val stats: List<String> = medicalSurgicalHistory!!.split("=")

            expectantSurgicalHistory = ExpectantMedicalSurgicalHistory(
                stats[0], stats[1],
                stats[2], stats[3],
                stats[4], stats[5],
                stats[6], stats[7],
                stats[8]
            )

            return expectantSurgicalHistory
        }

    }

}
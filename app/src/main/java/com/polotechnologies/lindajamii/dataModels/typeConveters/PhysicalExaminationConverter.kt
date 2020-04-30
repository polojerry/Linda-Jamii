package com.polotechnologies.lindajamii.dataModels.typeConveters

import androidx.room.TypeConverter
import com.polotechnologies.lindajamii.dataModels.PatientDetails.PhysicalExamination

class PhysicalExaminationConverter {
    @TypeConverter
    fun toString(physicalExamination: PhysicalExamination?): String {
        var examination = ""

        if (physicalExamination == null) {
            return examination
        } else {
            examination = "${physicalExamination.general}," +
                    "${physicalExamination.bp}," +
                    "${physicalExamination.height}," +
                    "${physicalExamination.cvs}," +
                    "${physicalExamination.resp}," +
                    "${physicalExamination.breast}," +
                    "${physicalExamination.abdomen}," +
                    "${physicalExamination.vaginalExamination}," +
                    physicalExamination.dischrge_genital_ulcer
        }


        return examination

    }

    @TypeConverter
    fun toPhysicalExamination(examination: String?): PhysicalExamination? {

        var physicalExamination: PhysicalExamination? = PhysicalExamination(
            "", "", "", "", "", "",
            "", "",""
        )

        if (examination.equals("")) {

            return physicalExamination

        } else {

            val stats: List<String> = examination!!.split(",")

            physicalExamination = PhysicalExamination(
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

            return physicalExamination
        }

    }
}
package com.polotechnologies.lindajamii.dataModels.typeConveters

import androidx.room.TypeConverter
import com.polotechnologies.lindajamii.dataModels.PatientDetails.AntenatalProfile

class AntenatalProfileConverter {
    @TypeConverter
    fun toString(antenatalProfile: AntenatalProfile?): String {
        var profile = ""

        if (antenatalProfile == null) {
            return profile
        } else {
            profile = "${antenatalProfile.hB}," +
                    "${antenatalProfile.blood_group}," +
                    "${antenatalProfile.rhesus_factor}," +
                    "${antenatalProfile.serology}," +
                    "${antenatalProfile.tb_screening_intense}," +
                    "${antenatalProfile.hiv}," +
                    "${antenatalProfile.urinalisys}," +
                    antenatalProfile.hiv_couseling_done
        }


        return profile

    }

    @TypeConverter
    fun toAntenatalProfile(profile: String?): AntenatalProfile? {

        var antenatalProfile: AntenatalProfile? = AntenatalProfile(
            "", "", "", "", "", "",
            "", ""
        )

        if (profile.equals("")) {

            return antenatalProfile

        } else {

            val stats: List<String> = profile!!.split(",")

            antenatalProfile = AntenatalProfile(
                stats[0],
                stats[1],
                stats[2],
                stats[3],
                stats[4],
                stats[5],
                stats[6],
                stats[7]
            )

            return antenatalProfile
        }

    }
}
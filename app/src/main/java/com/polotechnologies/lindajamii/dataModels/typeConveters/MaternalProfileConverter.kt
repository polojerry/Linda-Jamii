package com.polotechnologies.lindajamii.dataModels.typeConveters

import androidx.room.TypeConverter
import com.polotechnologies.lindajamii.dataModels.PatientDetails.MaternalProfile

class MaternalProfileConverter {
    @TypeConverter
    fun toString(maternalProfile: MaternalProfile?): String {
        var profile = ""

        if (maternalProfile == null) {
            return profile
        } else {
            profile = "${maternalProfile.institutionName}," +
                    "${maternalProfile.mfl_number}," +
                    "${maternalProfile.anc_number}," +
                    "${maternalProfile.pnc_number}," +
                    "${maternalProfile.name_of_client}," +
                    "${maternalProfile.age_of_client}," +
                    "${maternalProfile.gravida}," +
                    "${maternalProfile.parity}," +
                    "${maternalProfile.height}," +
                    "${maternalProfile.weight}," +
                    "${maternalProfile.marital_status}," +
                    "${maternalProfile.education}," +
                    "${maternalProfile.address}," +
                    "${maternalProfile.telephone}," +
                    "${maternalProfile.relationship}," +
                    maternalProfile.next_of_kin_contact
        }


        return profile

    }

    @TypeConverter
    fun toMaternalProfile(profile: String?): MaternalProfile? {

        var maternalProfile: MaternalProfile? = MaternalProfile(
            "", 0, 0, 0, "", 0,
            "", "", 0, 0, "", "", "", "", "", "", ""
        )

        if (profile.equals("")) {

            return maternalProfile

        } else {

            val stats: List<String> = profile!!.split(",")

            maternalProfile = MaternalProfile(
                    stats[0],
            stats[1].toInt(),
            stats[2].toInt(),
            stats[3].toInt(),
            stats[4],
            stats[5].toInt(),
            stats[6],
            stats[7],
            stats[8].toInt(),
            stats[9].toInt(),
            stats[10],
            stats[11],
            stats[12],
            stats[13],
            stats[14],
            stats[15],
            stats[16]
            )

            return maternalProfile
        }

    }
}
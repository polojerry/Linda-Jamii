package com.polotechnologies.lindajamii.database.typeConveters.old

import androidx.room.TypeConverter
import com.polotechnologies.lindajamii.dataModels.old.PatientDetails.MaternalProfile

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
                    "${maternalProfile.name_of_client}," +
                    "${maternalProfile.age_of_client}," +
                    "${maternalProfile.gravida}," +
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
            "", 0, 0, "", 0,
            "", 0.0, 0.0, "", "", "", "", "", "", ""
        )

        if (profile.equals("")) {

            return maternalProfile

        } else {

            val stats: List<String> = profile!!.split(",")

            maternalProfile = MaternalProfile(
                    stats[0],
            stats[1].toInt(),
            stats[2].toInt(),
            stats[3],
            stats[4].toInt(),
            stats[5],
            stats[6].toDouble(),
            stats[7].toDouble(),
            stats[8],
            stats[9],
            stats[10],
            stats[11],
            stats[12],
            stats[13],
            stats[14]
            )

            return maternalProfile
        }

    }
}
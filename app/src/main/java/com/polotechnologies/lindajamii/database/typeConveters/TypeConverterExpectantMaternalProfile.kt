package com.polotechnologies.lindajamii.database.typeConveters

import androidx.room.TypeConverter
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails.*

class TypeConverterExpectantMaternalProfile {
    @TypeConverter
    fun toString(expectantMaternalProfile: ExpectantMaternalProfile?): String {
        var maternalProfile = ""

        if (expectantMaternalProfile == null) {
            return maternalProfile
        } else {
            maternalProfile = "${expectantMaternalProfile.nameOfInstitution}=" +
                    "${expectantMaternalProfile.mflNumber}=" +
                    "${expectantMaternalProfile.ancNumber}=" +
                    "${expectantMaternalProfile.pncNumber}=" +
                    "${expectantMaternalProfile.nameOfClient}=" +
                    "${expectantMaternalProfile.ageOfClient}="+
                    "${expectantMaternalProfile.gravida}=" +
                    "${expectantMaternalProfile.parity}=" +
                    "${expectantMaternalProfile.height}=" +
                    "${expectantMaternalProfile.weight}=" +
                    "${expectantMaternalProfile.lmp}=" +
                    "${expectantMaternalProfile.edd}=" +
                    "${expectantMaternalProfile.maritalStatus}=" +
                    "${expectantMaternalProfile.education}=" +
                    "${expectantMaternalProfile.address}="+
                    "${expectantMaternalProfile.telephone}=" +
                    "${expectantMaternalProfile.nextOfKin}=" +
                    "${expectantMaternalProfile.relationShip}=" +
                    expectantMaternalProfile.nextOfKinContact
        }


        return maternalProfile

    }

    @TypeConverter
    fun toExpectantMaternalProfile(maternalProfile: String?): ExpectantMaternalProfile? {

        var expectantMaternalProfile: ExpectantMaternalProfile? = ExpectantMaternalProfile(
            "", "", "", "", "", "",
            "", "","","","","","","","",
            "","","",""
        )

        if (maternalProfile.equals("")) {

            return expectantMaternalProfile

        } else {

            val stats: List<String> = maternalProfile!!.split("=")

            expectantMaternalProfile = ExpectantMaternalProfile(
                stats[0],
                stats[1],
                stats[2],
                stats[3],
                stats[4],
                stats[5],
                stats[6],
                stats[7],
                stats[8],
                stats[9],
                stats[10],
                stats[11],
                stats[12],
                stats[13],
                stats[14],
                stats[15],
                stats[16],
                stats[17],
                stats[18])

            return expectantMaternalProfile
        }

    }
}
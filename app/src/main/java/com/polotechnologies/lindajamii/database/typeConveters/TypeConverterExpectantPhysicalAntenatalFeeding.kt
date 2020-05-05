package com.polotechnologies.lindajamii.database.typeConveters

import androidx.room.TypeConverter
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails.*

class TypeConverterExpectantPhysicalAntenatalFeeding {
    @TypeConverter
    fun toString(expectantPhysicalAntenatalFeeding: ExpectantPhysicalAntenatalFeeding?): String {
        var physicalAntenatalFeeding = ""

        if (expectantPhysicalAntenatalFeeding == null) {
            return physicalAntenatalFeeding
        } else {
            physicalAntenatalFeeding = "${expectantPhysicalAntenatalFeeding.general}," +
                    "${expectantPhysicalAntenatalFeeding.bp}," +
                    "${expectantPhysicalAntenatalFeeding.height}," +
                    "${expectantPhysicalAntenatalFeeding.cvs}," +
                    "${expectantPhysicalAntenatalFeeding.resp}," +
                    "${expectantPhysicalAntenatalFeeding.breasts}," +
                    "${expectantPhysicalAntenatalFeeding.abdomen}," +
                    "${expectantPhysicalAntenatalFeeding.vaginalExamination}," +
                    "${expectantPhysicalAntenatalFeeding.height}," +
                    "${expectantPhysicalAntenatalFeeding.dischargeGenitalUlcers}," +
                    "${expectantPhysicalAntenatalFeeding.hb}," +
                    "${expectantPhysicalAntenatalFeeding.bloodGroup}," +
                    "${expectantPhysicalAntenatalFeeding.rhesus}," +
                    "${expectantPhysicalAntenatalFeeding.serology}," +
                    "${expectantPhysicalAntenatalFeeding.tbScreening},"+
                    "${expectantPhysicalAntenatalFeeding.dateIPTIsonaziadGiven}," +
                    "${expectantPhysicalAntenatalFeeding.nextVisit}," +
                    "${expectantPhysicalAntenatalFeeding.hiv}," +
                    "${expectantPhysicalAntenatalFeeding.urianalysis},"+
                    "${expectantPhysicalAntenatalFeeding.givenHIVCounsellingAndTest}," +
                    "${expectantPhysicalAntenatalFeeding.feedingCounsellingDone}," +
                    expectantPhysicalAntenatalFeeding.counselingOnExclusiveBreastfeedingDone

        }

        return physicalAntenatalFeeding

    }

    @TypeConverter
    fun toExpectantMaternalProfile(expectantPhysicalAntenatalFeeding: String?): ExpectantPhysicalAntenatalFeeding? {

        var physicalAntenatalFeeding: ExpectantPhysicalAntenatalFeeding? =
            ExpectantPhysicalAntenatalFeeding(
                "", "", "", "", "", "",
                "", "", "", "", "", "", "", "", "",
                "", "", "", "","",""
            )

        if (expectantPhysicalAntenatalFeeding.equals("")) {

            return physicalAntenatalFeeding

        } else {

            val stats: List<String> = expectantPhysicalAntenatalFeeding!!.split(",")

            physicalAntenatalFeeding = ExpectantPhysicalAntenatalFeeding(
                stats[0], stats[1], stats[2],
                stats[3], stats[4], stats[5],
                stats[6], stats[7], stats[8],
                stats[9], stats[10], stats[11],
                stats[12], stats[13], stats[14],
                stats[15], stats[16], stats[17],
                stats[18], stats[19], stats[20]
            )

            return physicalAntenatalFeeding
        }

    }
}
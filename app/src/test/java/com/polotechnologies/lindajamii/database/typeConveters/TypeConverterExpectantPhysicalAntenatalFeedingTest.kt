package com.polotechnologies.lindajamii.database.typeConveters

import com.polotechnologies.lindajamii.dataModels.ExpectantDetails
import com.polotechnologies.lindajamii.dataModels.ExpectantDetails.*
import com.polotechnologies.lindajamii.ui.initialvisit.physicalAntenatalFeedingProfile.PhysicalAntenatalFeeding
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class TypeConverterExpectantPhysicalAntenatalFeedingTest {

    lateinit var typeConverterExpectantPhysicalAntenatalFeeding: TypeConverterExpectantPhysicalAntenatalFeeding

    @Before
    fun setUp() {
        typeConverterExpectantPhysicalAntenatalFeeding = TypeConverterExpectantPhysicalAntenatalFeeding()
    }

    @Test
    fun testToString() {

        val nullPhysicalAntenatalFeeding = null
        val expectedEmptyPhysicalAntenatalFeeding = ""

        val nullOutput = typeConverterExpectantPhysicalAntenatalFeeding.toString(nullPhysicalAntenatalFeeding)
        val output = typeConverterExpectantPhysicalAntenatalFeeding.toString(physicalAntenatalFeeding)

        assertEquals(expectedEmptyPhysicalAntenatalFeeding, nullOutput)
        assertEquals(expectedPhysicalAntenatalFeedingOutputString,output)
    }

    @Test
    fun toPhysicalAntenatalFeeding() {
        val emptyPhysicalAntenatalFeedingString = ""

        val expectedNullOutput = ExpectantPhysicalAntenatalFeeding()
        val nullOutput = typeConverterExpectantPhysicalAntenatalFeeding.toPhysicalAntenatalFeeding(emptyPhysicalAntenatalFeedingString)

        val expectedOutput  = physicalAntenatalFeeding
        val output = typeConverterExpectantPhysicalAntenatalFeeding.toPhysicalAntenatalFeeding(
            expectedPhysicalAntenatalFeedingOutputString
        )

        assertEquals(expectedNullOutput, nullOutput)
        assertEquals(expectedOutput, output)
    }

    companion object{
        private const val general = "Good"
        private const val bp = "100/70"
        private const val height = "1.82"
        private const val cvs = "Good"
        private const val resp = "Good"
        private const val breasts = "Good"
        private const val abdomen = "Good"
        private const val vaginalExamination = "Good"
        private const val dischargeGenitalUlcers = "No"
        private const val hb = "190/120"
        private const val bloodGroup = "O"
        private const val rhesus = "Positive"
        private const val serology = "Good"
        private const val tbScreening = "Good"
        private const val dateIPTIsonaziadGiven = "20/10/2020"
        private const val nextVisit = "21/11/2020"
        private const val hiv = "Reactive"
        private const val urianalysis = "Good"
        private const val givenHIVCounsellingAndTest = "Yes"
        private const val feedingCounsellingDone = "Yes"
        private const val counselingOnExclusiveBreastfeedingDone = "Yes"


        val physicalAntenatalFeeding = ExpectantPhysicalAntenatalFeeding(
            general, bp, height, cvs, resp, breasts, abdomen, vaginalExamination, dischargeGenitalUlcers,
            hb, bloodGroup, rhesus, serology, tbScreening, dateIPTIsonaziadGiven, nextVisit, hiv,
            urianalysis, givenHIVCounsellingAndTest, feedingCounsellingDone, counselingOnExclusiveBreastfeedingDone
        )

        const val expectedPhysicalAntenatalFeedingOutputString = "$general=$bp=$height=$cvs" +
                "=$resp=$breasts=$abdomen=$vaginalExamination=$dischargeGenitalUlcers=$hb=$bloodGroup=$rhesus" +
                "=$serology=$tbScreening=$dateIPTIsonaziadGiven=$nextVisit=$hiv=$urianalysis=$givenHIVCounsellingAndTest=$feedingCounsellingDone" +
                "=$counselingOnExclusiveBreastfeedingDone"
    }
}
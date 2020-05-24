package com.polotechnologies.lindajamii.database.typeConveters


import com.polotechnologies.lindajamii.dataModels.ExpectantDetails.ExpectantMedicalSurgicalHistory
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class TypeConverterExpectantMedicalSurgicalHistoryTest {

    lateinit var typeConverterExpectantMedicalSurgicalHistory: TypeConverterExpectantMedicalSurgicalHistory

    @Before
    fun setUp() {
        typeConverterExpectantMedicalSurgicalHistory = TypeConverterExpectantMedicalSurgicalHistory()
    }

    @Test
    fun testToString() {
        val nullMedicalSurgicalHistory = null
        val expectedNullMedicalSurgicalHistory = ""

        val nullOutput = typeConverterExpectantMedicalSurgicalHistory.toString(nullMedicalSurgicalHistory)
        val output = typeConverterExpectantMedicalSurgicalHistory.toString(medicalSurgicalHistory)

        assertEquals(expectedNullMedicalSurgicalHistory, nullOutput)
        assertEquals(expectedMedicalSurgicalHistoryOutputString,output)


    }

    @Test
    fun toExpectantMedicalSurgicalHistory() {
        val emptyMedicalSurgicalHistoryString = ""

        val expectedNullOutput = ExpectantMedicalSurgicalHistory()
        val nullOutput = typeConverterExpectantMedicalSurgicalHistory.toExpectantMedicalSurgicalHistory(emptyMedicalSurgicalHistoryString)

        val expectedOutput  = medicalSurgicalHistory
        val output = typeConverterExpectantMedicalSurgicalHistory.toExpectantMedicalSurgicalHistory(
            expectedMedicalSurgicalHistoryOutputString
        )

        assertEquals(expectedNullOutput, nullOutput)
        assertEquals(expectedOutput, output)

    }

    companion object{
        private const val surgicalOperation = "None"
        private const val diabetes = "No"
        private const val hypertension =  "No"
        private const val bloodTransfusion = "No"
        private const val tuberculosis = "No"
        private const val specificDrugAllergy = "Sulpher"
        private const val otherDrugAllergies=  "Penicillin"
        private const val familyHistoryTwins = "Yes"
        private const val familyHistoryTuberculosis = "No"



        val medicalSurgicalHistory = ExpectantMedicalSurgicalHistory(
            surgicalOperation,diabetes,hypertension,bloodTransfusion,tuberculosis,
            specificDrugAllergy,otherDrugAllergies,familyHistoryTwins,familyHistoryTuberculosis
        )

        const val expectedMedicalSurgicalHistoryOutputString = "$surgicalOperation=$diabetes=$hypertension=$bloodTransfusion" +
                "=$tuberculosis=$specificDrugAllergy=$otherDrugAllergies=$familyHistoryTwins=$familyHistoryTuberculosis"
    }

}
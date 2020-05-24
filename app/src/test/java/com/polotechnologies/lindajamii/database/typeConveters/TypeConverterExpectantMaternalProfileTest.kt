package com.polotechnologies.lindajamii.database.typeConveters

import com.polotechnologies.lindajamii.dataModels.ExpectantDetails.*
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class TypeConverterExpectantMaternalProfileTest {

    lateinit var typeConverterMaternalProfile: TypeConverterExpectantMaternalProfile

    @Before
    fun setup() {
        typeConverterMaternalProfile = TypeConverterExpectantMaternalProfile()
    }

    @Test
    fun testToString() {
        val nullExpectantProfile = null
        val expectedNullExpectantProfile = ""
        val nullOutput = typeConverterMaternalProfile.toString(nullExpectantProfile)


        val output = typeConverterMaternalProfile.toString(expectantProfile)

        assertEquals(expectedNullExpectantProfile, nullOutput)
        assertEquals(expectedExpectantOutputString,output)

    }

    @Test
    fun toExpectantMaternalProfile() {
        val nullExpectantProfileString = ""
        val expectedNullOutput = ExpectantMaternalProfile()
        val nullOutput = typeConverterMaternalProfile.toExpectantMaternalProfile(nullExpectantProfileString)

        val expectedOutput  = expectantProfile
        val output = typeConverterMaternalProfile.toExpectantMaternalProfile(expectedExpectantOutputString)

        assertEquals(expectedNullOutput, nullOutput)
        assertEquals(expectedOutput, output)

    }

    companion object{
        private const val nameOfInst = "Mur Malanga"
        private const val mflNumber = "100011"
        private const val ancNumber = "01/14"
        private const val pncNumber = "125"
        private const val clientName = "Winnie Achieng"
        private const val ageOfClient = "24"
        private const val gravida = "0"
        private const val parity = "0"
        private const val height = "1.8"
        private const val weight = "58"
        private const val lmp = "24/05/2020"
        private const val edd = "24/02/2021"
        private const val maritalStatus = "Single"
        private const val education = "University"
        private const val address = "7292, Eldoret"
        private const val telephone = "0790689212"
        private const val nextOfKin = "Jeremiah Polo"
        private const val relationShip = "Brother"
        private const val nextOfKinContact = "0790689212"


        val expectantProfile = ExpectantMaternalProfile(
            nameOfInst,mflNumber,ancNumber,pncNumber,clientName,ageOfClient,gravida,
            parity,height,weight,lmp,edd,maritalStatus,education,address,telephone,
            nextOfKin,relationShip,nextOfKinContact
        )

        const val expectedExpectantOutputString = "$nameOfInst=$mflNumber=$ancNumber=$pncNumber" +
                "=$clientName=$ageOfClient=$gravida=$parity=$height=$weight=$lmp=$edd=$maritalStatus" +
                "=$education=$address=$telephone=$nextOfKin=$relationShip=$nextOfKinContact"
    }
}
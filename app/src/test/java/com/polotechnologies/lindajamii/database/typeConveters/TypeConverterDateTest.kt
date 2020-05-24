package com.polotechnologies.lindajamii.database.typeConveters

import android.util.Log
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import java.time.LocalDate
import java.util.*

class TypeConverterDateTest {
    lateinit var typeConverterDate: TypeConverterDate

    @Before
    fun setUp() {
        typeConverterDate = TypeConverterDate()
    }

    @Test
    fun toLong() {
        val nullDate = null
        val date = dateToday

        val expectedNullOutput = 0L
        val nullOutput = typeConverterDate.toLong(nullDate)

        val expectedOutput = timeOfDayLong
        val output = typeConverterDate.toLong(date)


        assertEquals(expectedNullOutput, nullOutput)
        assertEquals(expectedOutput, output)
    }

    @Test
    fun toDate() {
        val input = 0L
        val timeInMill = timeOfDayLong

        val expectedNullOutput = null
        val expectedOutputDate = dateToday

        val nullOutput = typeConverterDate.toDate(input)
        val dateOutput = typeConverterDate.toDate(timeInMill)

        assertEquals(expectedNullOutput, nullOutput)
        assertEquals(expectedOutputDate,dateOutput)

    }

    companion object {
        val timeOfDayLong = Calendar.getInstance().timeInMillis
        val dateToday = Date(timeOfDayLong)
    }
}
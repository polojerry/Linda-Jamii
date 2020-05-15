package com.polotechnologies.lindajamii.util

import android.text.format.DateFormat
import java.util.Calendar

object DateConverter {
    const val DATE_FORMAT = "dd/MM/yy"

    fun getStringDate(dateInMilliseconds : Long) : String{
        val calender = Calendar.getInstance()
        calender.timeInMillis = dateInMilliseconds

        return DateFormat.format(DATE_FORMAT, calender).toString()
    }
}
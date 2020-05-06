package com.polotechnologies.lindajamii.database.typeConveters

import androidx.room.TypeConverter
import java.util.Date

class TypeConverterDate {
    @TypeConverter
    fun toLong(date: Date?): Long {
        return date?.time ?: 0L
    }

    @TypeConverter
    fun toDate(date: Long?): Date? {

        return if (date == 0L) {
            null
        } else {
            Date(date!!)
        }

    }
}
package com.polotechnologies.lindajamii.dataModels

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "subsequent_visit_table")
data class SubsequentVisit (
    @PrimaryKey
    val registration_id : Long,
    val number_of_visit : Int,
    val dateOfVisit : Date,
    var urineTest : String,
    val weight : Double,
    val hb :String,
    val pallor : String,
    val maturity : String,
    val fundal : String,
    val presentation : String,
    val lie :String,
    val foetalHealth : String,
    val foetalMovement: String,
    val nextVisit : Date
)
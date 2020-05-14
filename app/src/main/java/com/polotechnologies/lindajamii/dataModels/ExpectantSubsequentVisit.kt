package com.polotechnologies.lindajamii.dataModels

data class ExpectantSubsequentVisit(
    val registrationNumber: String = "",
    val numberOfVisit: String = "",
    val dateOfVisit: Long = 0L,
    val urine: String = "",
    val weight: String = "",
    val bp: String = "",
    val hb: String = "",
    val pallor: String = "",
    val maturity: String = "",
    val fundalHeight: String = "",
    val presentation: String = "",
    val lie: String = "",
    val foetalHeart: String = "",
    val foetalMovement: String = "",
    val nextVisit: Long = 0L
)
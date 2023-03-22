package com.devj.gestantescontrol.domain



data class DataDateForGestationalAge (
    val dateOfLastMenstruation: String,
    val weeksOfUS: Int = 0,
    val daysOfUS: Int = 0,
)

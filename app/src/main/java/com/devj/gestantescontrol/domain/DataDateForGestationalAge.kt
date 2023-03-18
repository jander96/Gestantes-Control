package com.devj.gestantescontrol.domain

import javax.inject.Inject

data class DataDateForGestationalAge @Inject constructor(
    val dateOfLastMenstruation: String,
    val weeksOfUS: Int = 0,
    val daysOfUS: Int = 0,
)

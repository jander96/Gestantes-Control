package com.devj.gestantescontrol.domain.model

data class DataDate(
    val fUM: String,
    val isFUMReliable: Boolean,
    val firstFUG: String,
    val firstUSWeeks: Int,
    val firstUSDays: Int,
    val secondFUG: String,
    val secondUSWeeks: Int,
    val secondUSDays: Int,
    val thirdFUG: String,
    val thirdUSWeeks: Int,
    val thirdUSDays: Int,
)
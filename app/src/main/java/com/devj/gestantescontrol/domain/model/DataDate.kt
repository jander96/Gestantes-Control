package com.devj.gestantescontrol.domain.model

data class DataDate(
    val fUM: String? = null,
    val isFUMReliable: Boolean = false,
    val firstFUG: String? = null,
    val firstUSWeeks: Int? = null,
    val firstUSDays: Int? = null,
    val secondFUG: String? = null,
    val secondUSWeeks: Int? = null,
    val secondUSDays: Int? = null,
    val thirdFUG: String? = null,
    val thirdUSWeeks: Int? = null,
    val thirdUSDays: Int? = null,
)
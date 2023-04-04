package com.devj.gestantescontrol.domain.model

data class Formulary(
    val id: Int,
    val name: String,
    val lastName: String,
    val age : String? = null,
    val weight : String? = null,
    val size : String?= null,
    val phoneNumber: String? = null,
    val fUM: String? = null,
    val isFUMReliable: Boolean = false,
    val firstFUG: String? = null,
    val firstUSWeeks: String? = null,
    val firstUSDays: String? = null,
    val secondFUG: String? = null,
    val secondUSWeeks: String? = null,
    val secondUSDays: String? = null,
    val thirdFUG: String? = null,
    val thirdUSWeeks: String? = null,
    val thirdUSDays: String? = null,
    val riskFactors: String? = null,
    val notes: String? = null,
    val photo: String? = null
)
package com.devj.gestantescontrol.domain.model


data class Pregnant(
    val id: Int = 0,
    val name: String,
    val lastName: String,
    val age: Int? = null,
    val phoneNumber: String? = null,
    val measures: Measures? = null,
    val dataDate: DataDate,
    val riskFactors: List<RiskFactor>? = null,
    val notes: String? = null,
    val photo: String? = null
)






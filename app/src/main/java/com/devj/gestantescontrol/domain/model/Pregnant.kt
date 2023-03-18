package com.devj.gestantescontrol.domain.model


data class Pregnant(
    val id: Int,
    val name: String,
    val lastName: String,
    val age: String,
    val phoneNumber: String,
    val measures: Measures,
    val dataDate: DataDate,
    val riskFactors: List<RiskFactor>,
    val notes: String,
    val photo: String
)






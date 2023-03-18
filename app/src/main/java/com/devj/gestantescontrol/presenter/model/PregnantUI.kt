package com.devj.gestantescontrol.presenter.model


data class PregnantUI(
    val fullName: String,
    val age: Int,
    val phoneNumber: String,
    val iMC: Double,
    val isFUMReliable: Boolean,
    val gestationalAgeByFUM: String,
    val gestationalAgeByFirstUS: String,
    val gestationalAgeBySecondUS: String,
    val gestationalAgeByThirdUS: String,
    val riskClassification: String,
    val listOfRiskFactors: String,
    val notes: String,
    val photo: String
)



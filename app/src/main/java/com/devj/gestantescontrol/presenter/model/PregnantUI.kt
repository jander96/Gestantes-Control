package com.devj.gestantescontrol.presenter.model


import java.io.Serializable

data class PregnantUI(
    val id: Int,
    val fullName: String,
    val age: String,
    val phoneNumber: String,
    val iMC: String,
    val isFUMReliable: Boolean,
    val gestationalAgeByFUM: String,
    val gestationalAgeByFirstUS: String,
    val gestationalAgeBySecondUS: String,
    val gestationalAgeByThirdUS: String,
    val fpp : String,
    val riskClassification: String,
    val listOfRiskFactors: String,
    val notes: String,
    val photo: String
) : Serializable








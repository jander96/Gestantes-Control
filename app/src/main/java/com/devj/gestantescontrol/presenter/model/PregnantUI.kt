package com.devj.gestantescontrol.presenter.model

import com.devj.gestantescontrol.domain.DataDateForGestationalAge
import com.devj.gestantescontrol.domain.GestationalAgeCalculator
import com.devj.gestantescontrol.domain.model.Pregnant
import com.devj.gestantescontrol.domain.model.RiskFactor
import java.io.Serializable
import java.util.Date


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
    val riskClassification: String,
    val listOfRiskFactors: String,
    val notes: String,
    val photo: String
) : Serializable {

    companion object {
        fun fromDomain(
            pregnant: Pregnant,
            gestationalAgeCalculator: GestationalAgeCalculator
        ): PregnantUI {
            val dataDateFUM = DataDateForGestationalAge(
                pregnant.dataDate.fUM ?: ""
            )
            val dataDateFirstUS = pregnant.dataDate.firstUSWeeks?.let { weeks ->
                pregnant.dataDate.firstUSDays?.let { days ->
                    DataDateForGestationalAge(pregnant.dataDate.firstFUG ?: "", weeks, days)
                }
            }
            val dataDateSecondUS = pregnant.dataDate.secondUSWeeks?.let { weeks ->
                pregnant.dataDate.secondUSDays?.let { days ->
                    DataDateForGestationalAge(pregnant.dataDate.secondFUG ?: "", weeks, days)
                }
            }
            val dataDateThirdUS = pregnant.dataDate.thirdUSWeeks?.let { weeks ->
                pregnant.dataDate.thirdUSDays?.let { days ->
                    DataDateForGestationalAge(pregnant.dataDate.thirdFUG ?: "", weeks, days)
                }
            }

            return PregnantUI(
                pregnant.id,
                pregnant.name + " " + pregnant.lastName,
                pregnant.age.toString(),
                pregnant.phoneNumber ?: "",
                if (pregnant.measures != null)
                    getIMC(pregnant.measures.weight, pregnant.measures.size).toString()
                else "Sin datos",
                pregnant.dataDate.isFUMReliable,
                if (pregnant.dataDate.fUM != null )
                    gestationalAgeCalculator.getByFUM(dataDateFUM)
                else "Sin datos",
                if (dataDateFirstUS != null)
                    gestationalAgeCalculator.getByUS(dataDateFirstUS)
                else "No tiene U/S correspondiente",
                if (dataDateSecondUS != null)
                    gestationalAgeCalculator.getByUS(dataDateSecondUS)
                else "No tiene U/S correspondiente",
                if (dataDateThirdUS != null)
                    gestationalAgeCalculator.getByUS(dataDateThirdUS)
                else "No tiene U/S correspondiente",
                getRiskClassification(pregnant.riskFactors),
                if (pregnant.riskFactors != null)
                    getStringFromList(pregnant.riskFactors)
                else "No tiene riesgos asociados",
                pregnant.notes ?: "",
                pregnant.photo ?: ""
            )
        }

        private fun getIMC(weight: Double, size: Double) = weight / (size * size)
        private fun getRiskClassification(listOfRisk: List<RiskFactor>?): String {
            return if (listOfRisk != null && listOfRisk.size > 2) "Alto riesgo" else "Bajo riesgo"
        }

        private fun getStringFromList(listOfRiskFactors: List<RiskFactor>): String {
            return listOfRiskFactors.joinToString("/ ") { it.name }
        }
    }
}








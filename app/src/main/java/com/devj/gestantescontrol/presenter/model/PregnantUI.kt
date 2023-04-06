package com.devj.gestantescontrol.presenter.model


import com.devj.gestantescontrol.domain.GestationalAgeCalculator
import com.devj.gestantescontrol.domain.model.Pregnant
import com.devj.gestantescontrol.domain.model.RiskFactor
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

            return with(pregnant) {

                PregnantUI(
                    id,
                    "$name $lastName",
                    age.toString(),
                    phoneNumber ?: "",
                    if (measures != null)
                        getIMC(measures.weight, measures.size).toString()
                    else "Sin datos",
                    dataDate.isFUMReliable,
                    if (dataDate.fUM != null)
                        gestationalAgeCalculator.getByFUM(dataDate.fUM)
                    else "Sin datos",
                    if (dataDate.firstFUG != null &&
                        dataDate.firstUSWeeks != null &&
                        dataDate.firstUSDays != null
                    )
                        gestationalAgeCalculator.getByUS(
                            dataDate.firstFUG,
                            dataDate.firstUSWeeks,
                            dataDate.firstUSDays
                        )
                    else "Sin Datos",
                    if (dataDate.secondFUG != null &&
                        dataDate.secondUSWeeks != null &&
                        dataDate.secondUSDays != null
                    )
                        gestationalAgeCalculator.getByUS(
                            dataDate.secondFUG,
                            dataDate.secondUSWeeks,
                            dataDate.secondUSDays
                        )
                    else "Sin Datos",
                    if (dataDate.thirdFUG != null &&
                        dataDate.thirdUSWeeks != null &&
                        dataDate.thirdUSDays != null
                    )
                        gestationalAgeCalculator.getByUS(
                            dataDate.thirdFUG,
                            dataDate.thirdUSWeeks,
                            dataDate.thirdUSDays
                        )
                    else "Sin Datos",
                    getRiskClassification(pregnant.riskFactors),
                    if (pregnant.riskFactors != null)
                        getStringFromList(pregnant.riskFactors)
                    else "No tiene riesgos asociados",
                    pregnant.notes ?: "",
                    pregnant.photo ?: ""
                )
            }
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








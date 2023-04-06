package com.devj.gestantescontrol.presenter.model

import com.devj.gestantescontrol.domain.model.Pregnant
import com.devj.gestantescontrol.domain.model.RiskFactor
import com.devj.gestantescontrol.domain.model.USData
import com.devj.gestantescontrol.domain.usescases.CalculateByFUM
import com.devj.gestantescontrol.domain.usescases.CalculateByUS
import com.devj.gestantescontrol.domain.usescases.CalculateFPP
import java.util.Calendar
import javax.inject.Inject

class UIMapper @Inject constructor(
    private val calculateByFUM: CalculateByFUM,
    private val calculateByUS: CalculateByUS,
    private val calculateFPP: CalculateFPP
) {

    fun fromDomain(pregnant: Pregnant, calendar: Calendar): PregnantUI {
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
                    calculateByFUM(dataDate.fUM, calendar)
                else "Sin datos",
                if (dataDate.firstFUG != null &&
                    dataDate.firstUSWeeks != null &&
                    dataDate.firstUSDays != null
                )
                    calculateByUS(
                        dataDate.firstFUG,
                        dataDate.firstUSWeeks,
                        dataDate.firstUSDays,
                        calendar
                    )
                else "Sin Datos",
                if (dataDate.secondFUG != null &&
                    dataDate.secondUSWeeks != null &&
                    dataDate.secondUSDays != null
                )
                    calculateByUS(
                        dataDate.secondFUG,
                        dataDate.secondUSWeeks,
                        dataDate.secondUSDays,
                        calendar
                    )
                else "Sin Datos",
                if (dataDate.thirdFUG != null &&
                    dataDate.thirdUSWeeks != null &&
                    dataDate.thirdUSDays != null
                )
                    calculateByUS(
                        dataDate.thirdFUG,
                        dataDate.thirdUSWeeks,
                        dataDate.thirdUSDays,
                        calendar
                    )
                else "Sin Datos",
                calculateFPP(
                    calendar,
                    dataDate.fUM,
                    USData(dataDate.firstFUG, dataDate.firstUSWeeks, dataDate.firstUSDays)
                ),
                        getRiskClassification (pregnant.riskFactors),
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
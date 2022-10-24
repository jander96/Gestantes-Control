package com.devj.gestantescontrol.domain


private const val CANT_DIAS_X_SEMANA = 7


class CalculadoraEG(
    private val fecha: String,
    private val semanasEnUSG: Int = 0,
    private val diasEnUSG: Int = 0,
    private val calculadoraFechas:CalcDates
) {
    private var edadGestacionalFUM = ""
    private var edadGestacionalFUG = ""

    fun calcularPorFUM(): String {
        val diasDesdeFUM = calculadoraFechas.getDiferenciaDias(fecha)
        edadGestacionalFUM =
            "${diasDesdeFUM / CANT_DIAS_X_SEMANA}." + "${diasDesdeFUM % CANT_DIAS_X_SEMANA}"
        return if (edadGestacionalFUM.toFloat() < 42f) {
            edadGestacionalFUM
        } else " Postérmino"
    }

    fun calcularPorUSG(): String {

        val cantDiasDesdeUSG = calculadoraFechas.getDiferenciaDias(fecha)

        val cantTotalDias = diasEnUSG + (cantDiasDesdeUSG % CANT_DIAS_X_SEMANA)

        val cantTotalSemanas =
            if (cantTotalDias < CANT_DIAS_X_SEMANA) semanasEnUSG + (cantDiasDesdeUSG / CANT_DIAS_X_SEMANA)
            else (semanasEnUSG + (cantDiasDesdeUSG / CANT_DIAS_X_SEMANA)) + 1

        val diasDefinitivos =
            if (cantTotalDias < CANT_DIAS_X_SEMANA) cantTotalDias
            else cantTotalDias - CANT_DIAS_X_SEMANA

        edadGestacionalFUG = "${cantTotalSemanas}." + "$diasDefinitivos"
        return if (edadGestacionalFUG.toFloat() < 42f) {
            edadGestacionalFUG
        } else {
            " Postérmino"
        }
    }

    fun calcularFPP(fum: String = "0/0/0", fug: String = "0/0/0"): String {
        return if (fum != "0/0/0") {
            calculadoraFechas.sumarFechas(fum, 40)
        } else {
            calculadoraFechas.sumarFechas(
                fug, if (diasEnUSG == 0) {
                    40 - semanasEnUSG
                } else 41 - semanasEnUSG
            )
        }

    }

}



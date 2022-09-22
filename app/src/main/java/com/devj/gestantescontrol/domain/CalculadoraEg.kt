package com.devj.gestantescontrol.data.model

import java.text.SimpleDateFormat
import java.util.*

private const val CANT_DIAS_X_SEMANA = 7


class CalculadoraEg(
    private val fecha: String,
    private val semanasEnUSG: Int = 0,
    private val diasEnUSG: Int = 0
) {
    private var edadGestacionalFUM = ""
    private var edadGestacionalFUG = ""

    fun calcularPorFUM(): String {
        val diasDesdeFUM = CalculadoraFechas.getDiferenciaDias(fecha)
        edadGestacionalFUM =
            "${diasDesdeFUM / CANT_DIAS_X_SEMANA}." + "${diasDesdeFUM % CANT_DIAS_X_SEMANA}"
        return if (edadGestacionalFUM.toFloat() < 42f){
            edadGestacionalFUM
        } else " Postérmino"
    }

    fun calcularPorUSG(): String {

        val cantDiasDesdeUSG = CalculadoraFechas.getDiferenciaDias(fecha)

        val cantTotalDias = diasEnUSG + (cantDiasDesdeUSG % CANT_DIAS_X_SEMANA)

        val cantTotalSemanas =
            if (cantTotalDias < CANT_DIAS_X_SEMANA) semanasEnUSG + (cantDiasDesdeUSG / CANT_DIAS_X_SEMANA)
            else (semanasEnUSG + (cantDiasDesdeUSG / CANT_DIAS_X_SEMANA)) + 1

        val diasDefinitivos =
            if (cantTotalDias < CANT_DIAS_X_SEMANA) cantTotalDias
            else cantTotalDias - CANT_DIAS_X_SEMANA

        edadGestacionalFUG = "${cantTotalSemanas}." + "$diasDefinitivos"
        return if( edadGestacionalFUG.toFloat() < 42f){
            edadGestacionalFUG
        }else{
            " Postérmino"
        }
    }

    fun calcularFPP(fum: String = "", fug: String = ""): String {
        return if (fum != "") {
            CalculadoraFechas.sumarFechas(fum, 40)
        } else {
            CalculadoraFechas.sumarFechas(
                fug, if (diasEnUSG == 0) {
                    40 - semanasEnUSG
                } else 41 - semanasEnUSG
            )
        }

    }

}

object CalculadoraFechas {

    private const val CANT_MILLIS_X_DIA = 86400000

    fun getDiferenciaDias(fecha: String): Int {
        //Obtengo la fecha de hoy en Millisegundos desde el sistema
        val fechaActualInMillis = Calendar.getInstance().timeInMillis

        // Obtengo la fecha q se pasa  al constructor en millisegundos
        val fechaEscogida = SimpleDateFormat("dd/MM/yyyy")
            .parse(fecha)
        val calendar = Calendar.getInstance()
        calendar.time = fechaEscogida

        val fechaEscogidaInMillis = calendar.timeInMillis

        return ((fechaActualInMillis - fechaEscogidaInMillis) / CANT_MILLIS_X_DIA).toInt()
    }

    fun sumarFechas(fecha: String, cantSemanas: Int): String {
        val calendar = Calendar.getInstance()
        val fechaFormat = SimpleDateFormat("dd/MM/yyyyy").parse(fecha)
        calendar.time = fechaFormat
        calendar.add(Calendar.WEEK_OF_YEAR,cantSemanas)

        val dia = calendar.get(Calendar.DAY_OF_MONTH)
        val mes = calendar.get(Calendar.MONTH)
        val ano = calendar.get(Calendar.YEAR)
        return "${dia}/${mes+1}/${ano}"

    }
}

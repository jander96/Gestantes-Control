package com.devj.gestantescontrol.framework

import com.devj.gestantescontrol.domain.CalcDates
import java.text.SimpleDateFormat
import java.util.Calendar

class CalcDatesImpl:CalcDates {

   companion object{ private const val CANT_MILLIS_X_DIA = 86400000}

    override fun getDiferenciaDias(fecha: String): Int {
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

    override fun sumarFechas(fecha: String, cantSemanas: Int): String {
        val calendar = Calendar.getInstance()
        val fechaFormat = SimpleDateFormat("dd/MM/yyyyy").parse(fecha)
        calendar.time = fechaFormat
        calendar.add(Calendar.WEEK_OF_YEAR, cantSemanas)

        val dia = calendar.get(Calendar.DAY_OF_MONTH)
        val mes = calendar.get(Calendar.MONTH)
        val ano = calendar.get(Calendar.YEAR)
        return "${dia}/${mes + 1}/${ano}"

    }
}
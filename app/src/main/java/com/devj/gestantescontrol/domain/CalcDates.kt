package com.devj.gestantescontrol.domain

interface CalcDates {
    fun getDiferenciaDias(fecha:String):Int
    fun sumarFechas(fecha: String,cantSemanas:Int):String
}
package com.devj.gestantescontrol.domain

interface DateCalculator {
    fun getDaysDiff(dateInStringType : String):Int
    fun addWeeksToADate(dateInStringType : String, weeks : Int):String
}
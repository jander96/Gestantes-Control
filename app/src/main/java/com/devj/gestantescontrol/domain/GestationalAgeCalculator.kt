package com.devj.gestantescontrol.domain





private const val CANT_DIAS_X_SEMANA = 7


class GestationalAgeCalculator (
    private val dateCalculator: DateCalculator
) {

    fun getByFUM(date: String): String {
        val daysDiff = dateCalculator.getDaysDiff(date)
        val gestationalAge =
            "${daysDiff / CANT_DIAS_X_SEMANA}." + "${daysDiff % CANT_DIAS_X_SEMANA}"
        return if (gestationalAge.toFloat() < 42f) gestationalAge else " Postérmino"
    }

    fun getByUS(date: String,weeks: Int, days: Int): String {
        val diffOfDaysFromUS = dateCalculator.getDaysDiff(date)
        val totalDiffOfDays = days + (diffOfDaysFromUS % CANT_DIAS_X_SEMANA)
        val weeks =
            if (totalDiffOfDays < CANT_DIAS_X_SEMANA) {
                weeks + (diffOfDaysFromUS / CANT_DIAS_X_SEMANA)
            } else {
                (weeks+ (diffOfDaysFromUS / CANT_DIAS_X_SEMANA)) + 1
            }
        val days =
            if (totalDiffOfDays < CANT_DIAS_X_SEMANA) totalDiffOfDays
            else totalDiffOfDays - CANT_DIAS_X_SEMANA

        val gestationalAge = "${weeks}." + "$days"
        return if (gestationalAge.toFloat() < 42f) gestationalAge else " Postérmino"
    }


}



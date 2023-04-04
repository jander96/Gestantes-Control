package com.devj.gestantescontrol.domain

import android.util.Log
import javax.inject.Inject


private const val CANT_DIAS_X_SEMANA = 7


class GestationalAgeCalculator @Inject constructor(
    private val dateCalculator: DateCalculator
) {

    fun getByFUM(dataDateForGestationalAge: DataDateForGestationalAge): String {
        val daysDiff = dateCalculator.getDaysDiff(dataDateForGestationalAge.dateOfLastMenstruation)
        val gestationalAge =
            "${daysDiff / CANT_DIAS_X_SEMANA}." + "${daysDiff % CANT_DIAS_X_SEMANA}"
        Log.d("Calculator","gestational age = $gestationalAge")
        return if (gestationalAge.toFloat() < 42f) gestationalAge else " Postérmino"
    }

    fun getByUS(dataDateForGestationalAge: DataDateForGestationalAge): String {
        val diffOfDaysFromUS = dateCalculator.getDaysDiff(dataDateForGestationalAge.dateOfLastMenstruation)
        val totalDiffOfDays = dataDateForGestationalAge.daysOfUS + (diffOfDaysFromUS % CANT_DIAS_X_SEMANA)
        val weeks =
            if (totalDiffOfDays < CANT_DIAS_X_SEMANA) {
                dataDateForGestationalAge.weeksOfUS + (diffOfDaysFromUS / CANT_DIAS_X_SEMANA)
            } else {
                (dataDateForGestationalAge.weeksOfUS + (diffOfDaysFromUS / CANT_DIAS_X_SEMANA)) + 1
            }
        val days =
            if (totalDiffOfDays < CANT_DIAS_X_SEMANA) totalDiffOfDays
            else totalDiffOfDays - CANT_DIAS_X_SEMANA

        val gestationalAge = "${weeks}." + "$days"
        return if (gestationalAge.toFloat() < 42f) gestationalAge else " Postérmino"
    }

    fun getFPP(dataDateForGestationalAge: DataDateForGestationalAge, dateByFUM: String? = null, dateByUS: String? = null): String {
        val WEKKS_IF_US_WEEKS_IS_EXACT = 40
        val WEKKS_IF_US_WEEKS_ISNOT_EXACT = 41
        val weeks = if (dataDateForGestationalAge.daysOfUS == 0) WEKKS_IF_US_WEEKS_IS_EXACT - dataDateForGestationalAge.weeksOfUS
        else WEKKS_IF_US_WEEKS_ISNOT_EXACT - dataDateForGestationalAge.weeksOfUS

        return if (dateByFUM != null)
            dateCalculator.addWeeksToADate(dateByFUM, 40)
        else if(dateByUS != null) dateCalculator.addWeeksToADate(dateByUS, weeks)
        else "Sin datos"
    }

}



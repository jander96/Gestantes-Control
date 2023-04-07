package com.devj.gestantescontrol.domain.usescases

import com.devj.gestantescontrol.domain.Constans
import com.devj.gestantescontrol.domain.DateCalculator
import java.util.Calendar
import javax.inject.Inject

class CalculateByUS @Inject constructor(private val dateCalculator: DateCalculator) {

    operator fun invoke(date: String,weeks: Int, days: Int): String {
        val calendar = Calendar.getInstance()
        val diffOfDaysFromUS = dateCalculator.getDaysDiff(date,calendar)
        val totalDiffOfDays = days + (diffOfDaysFromUS % Constans.DAYS_BY_WEEKS)
        val weeks =
            if (totalDiffOfDays < Constans.DAYS_BY_WEEKS) {
                weeks + (diffOfDaysFromUS / Constans.DAYS_BY_WEEKS)
            } else {
                (weeks+ (diffOfDaysFromUS / Constans.DAYS_BY_WEEKS)) + 1
            }
        val days =
            if (totalDiffOfDays < Constans.DAYS_BY_WEEKS) totalDiffOfDays
            else totalDiffOfDays - Constans.DAYS_BY_WEEKS

        val gestationalAge = "${weeks}." + "$days"
        return if (gestationalAge.toFloat() < 42f) gestationalAge else " Postérmino"
    }
}
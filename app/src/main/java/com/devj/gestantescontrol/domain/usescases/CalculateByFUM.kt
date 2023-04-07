package com.devj.gestantescontrol.domain.usescases

import com.devj.gestantescontrol.domain.Constans.DAYS_BY_WEEKS
import com.devj.gestantescontrol.domain.DateCalculator
import java.util.Calendar
import javax.inject.Inject

class CalculateByFUM @Inject constructor(private val dateCalculator: DateCalculator){

  operator fun invoke (date: String): String {
      val calendar = Calendar.getInstance()
        val daysDiff = dateCalculator.getDaysDiff(date,calendar)
        val gestationalAge =
            "${daysDiff / DAYS_BY_WEEKS}." + "${daysDiff % DAYS_BY_WEEKS}"
        return if (gestationalAge.toFloat() < 42f) gestationalAge else " Postérmino"
    }
}
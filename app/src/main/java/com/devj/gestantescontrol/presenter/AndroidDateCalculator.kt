package com.devj.gestantescontrol.presenter

import com.devj.gestantescontrol.domain.DateCalculator
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class AndroidDateCalculator @Inject constructor() : DateCalculator {
    private val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    private val calendar = Calendar.getInstance()
    private val regexForCorrectDateFormat = Regex(DATE_REGEX_FORMAT)
    companion object {
        private const val CANT_MILLIS_X_DIA = 86400000
        private const val DATE_REGEX_FORMAT = "^([1-9]|0[1-9]|[12]\\d|3[01])/([1-9]|0[1-9]|1[0-2])/(\\d{4})\$"
        private const val EXCEPTION_MESSAGE = "Invalid string format for date in dd/MM/yyyy"
    }

    override fun getDaysDiff(dateInStringType: String): Int {
        return if (dateInStringType.matches(regexForCorrectDateFormat)) {
            val currentDaysInMillis = calendar.timeInMillis
            val date = dateFormatter.parse(dateInStringType)
            calendar.time = date!!
            ((currentDaysInMillis - calendar.timeInMillis) / CANT_MILLIS_X_DIA).toInt()
        } else {
            throw Exception(EXCEPTION_MESSAGE)
        }
    }

    override fun addWeeksToADate(dateInStringType: String, weeks: Int): String {
        return if (dateInStringType.matches(regexForCorrectDateFormat)) {
        calendar.time = dateFormatter.parse(dateInStringType)!!
        calendar.add(Calendar.WEEK_OF_YEAR, weeks)

        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)+1
        val year = calendar.get(Calendar.YEAR)

        "${day}/${month}/${year}"

        }else{
            throw Exception(EXCEPTION_MESSAGE)
        }
    }
}
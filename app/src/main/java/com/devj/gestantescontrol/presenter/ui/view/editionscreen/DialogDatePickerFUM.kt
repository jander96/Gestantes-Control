package com.devj.gestantescontrol.presenter.ui.view.editionscreen

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

class DialogDatePickerFUM(
    val onDateSelected: (year: Int, month: Int, day: Int) -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val currentYear = c.get(Calendar.YEAR)
        val currentMonth = c.get(Calendar.MONTH)
        val currentDay = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(
            requireContext(),
            { view,
              year, month, day ->
                onDateSelected(year, month, day)
            },
            currentYear, currentMonth, currentDay
        )
    }
}
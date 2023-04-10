package com.devj.gestantescontrol.presenter.ui.view.editionscreen


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.devj.gestantescontrol.R
import com.google.android.material.textfield.TextInputLayout


class DialogDatePickerUS(
    private val onTimeSelected: (year: Int, month: Int, day: Int, weeksOnUS: Int, dayOnUS: Int,usTrimester: Int) -> Unit
) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val usTrimester = arguments?.getInt(FragmentEdition.PICKER_KEY_ARGS)
        val view = inflater.inflate(R.layout.fragment_date_picker, container, false)
        view.findViewById<Button>(R.id.btn_aceptar).setOnClickListener {
            val etWeeks = view.findViewById<EditText>(R.id.et_weeks)
            val etDays =  view.findViewById<EditText>(R.id.et_days)
            val inPutLayout = view.findViewById<TextInputLayout>(R.id.input_layout_eg)

            if(etWeeks.text.isEmpty()||etDays.text.isEmpty()||
                etDays.text.toString().toInt()>6|| etWeeks.text.toString().toInt()>42){
                inPutLayout.error= context?.getString(R.string.date_picker_us_error_messange)
            }else{
                inPutLayout.isErrorEnabled = false
                val calendar = view.findViewById<DatePicker>(R.id.date_picker)
                val year = calendar.year
                val month = calendar.month
                val day = calendar.dayOfMonth
                val weeksOnUS = etWeeks.text.toString().toInt()
                val daysOnUS = etDays.text.toString().toInt()
                onTimeSelected(year,month+1,day,weeksOnUS,daysOnUS,usTrimester!!)
                dismiss()
            }
        }
        view.findViewById<Button>(R.id.btn_cancel).setOnClickListener {
            dismiss()
        }
        return view
    }


}
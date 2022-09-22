package com.devj.gestantescontrol.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.devj.gestantescontrol.databinding.FragmentCalendarioUgBinding
import com.devj.gestantescontrol.ui.view.CalendarioUGFragmentArgs
import com.devj.gestantescontrol.ui.view.CalendarioUGFragmentDirections
import java.util.*

class CalendarioUGViewModel:ViewModel() {

    private val aYearInMillis = 31557600000
    val today = Calendar.getInstance().timeInMillis
    val aYearAgo = Calendar.getInstance().timeInMillis - aYearInMillis
    fun enviarRegistros(binding:FragmentCalendarioUgBinding, args:CalendarioUGFragmentArgs, navController: NavController){
        if(binding.etSemanas.text.isEmpty()||binding.etDias.text.isEmpty()||
            binding.etDias.text.toString().toInt()>6|| binding.etSemanas.text.toString().toInt()>42){
            binding.inputLayoutEg.error="Campos incorrectos o vacios"
        }else{
            val diaUS = binding.datePickerUg.dayOfMonth
            val mesUS = binding.datePickerUg.month
            val anoUS = binding.datePickerUg.year
            val cantSemanas = binding.etSemanas.text.toString().toInt()
            val cantDias = binding.etDias.text.toString().toInt()

            val action = CalendarioUGFragmentDirections.ugToEdicion(
                id= args.id,
                fug="${diaUS}/${mesUS+1}/${anoUS}",
                fumConfiable = args.fumConfiable,
                cantSemanas = cantSemanas,
                cantDias = cantDias,
                nombre = args.nombre,
                apellidos = args.apellidos,
                edad = args.edad,
                peso = args.peso,
                talla = args.talla,
                telefono = args.telefono,
                nota = args.nota,
                fum = args.fum,
                foto = args.foto
            )
            navController.navigate(action)

        }
    }
}
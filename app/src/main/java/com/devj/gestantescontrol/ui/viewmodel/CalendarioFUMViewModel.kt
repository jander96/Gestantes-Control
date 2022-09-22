package com.devj.gestantescontrol.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.devj.gestantescontrol.databinding.FragmentCalendarioFumBinding
import com.devj.gestantescontrol.ui.view.CalendarioFUMFragmentArgs
import com.devj.gestantescontrol.ui.view.CalendarioFUMFragmentDirections
import java.util.*

class CalendarioFUMViewModel : ViewModel() {
    private val aYearInMillis = 31557600000
    val today = Calendar.getInstance().timeInMillis
    val aYearAgo = Calendar.getInstance().timeInMillis - aYearInMillis
    fun navigateAndpassData(
        binding: FragmentCalendarioFumBinding,
        args: CalendarioFUMFragmentArgs,
        navController: NavController
    ) {
        val diaFUM = binding.datePickerFum.dayOfMonth
        val mesFUM = binding.datePickerFum.month
        val anoFUM = binding.datePickerFum.year

        navController.navigate(
            CalendarioFUMFragmentDirections.fumToEdicion(
                id = args.id,
                fum = "${diaFUM}/${mesFUM+1}/${anoFUM}",
                fumConfiable = args.fumConfiable,
                nombre = args.nombre,
                apellidos = args.apellidos,
                edad = args.edad,
                peso = args.peso,
                talla = args.talla,
                telefono = args.telefono,
                nota = args.nota,
                fug = args.fug,
                foto = args.foto
            )
        )
    }
}
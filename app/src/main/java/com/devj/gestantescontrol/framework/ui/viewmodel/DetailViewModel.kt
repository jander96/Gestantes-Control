package com.devj.gestantescontrol.framework.ui.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.*
import androidx.navigation.NavController
import com.devj.gestantescontrol.R
import com.devj.gestantescontrol.core.getBitmapFromFile
import com.devj.gestantescontrol.domain.CalculadoraEG
import com.devj.gestantescontrol.databinding.FragmentDetailBinding
import com.devj.gestantescontrol.domain.*
import com.devj.gestantescontrol.framework.CalcDatesImpl
import com.devj.gestantescontrol.framework.ui.view.GestanteDetailFragmentArgs
import com.devj.gestantescontrol.framework.ui.view.GestanteDetailFragmentDirections
import com.devj.gestantescontrol.usescases.CalculadoraFPPUseCase
import com.devj.gestantescontrol.usescases.CalcularEGXFUMUseCase
import com.devj.gestantescontrol.usescases.CalcularEGXUGUseCase
import com.devj.gestantescontrol.usescases.EliminarGestanteUseCase
import java.text.DecimalFormat

class DetailViewModel(
    private val repo: Repo
) : ViewModel() {
    private val _gestante = MutableLiveData<Gestante>()
    val gestante: LiveData<Gestante> get() = _gestante

    fun initGestanteValue(args: GestanteDetailFragmentArgs) {
        _gestante.value = args.gestante.toGestante()
    }

    private fun egGuia(
        fumConfiable: Boolean,
        fum: String,
        fug: String,
        cantSemUg: String,
        cantDiasUg: String
    ): String {

        return if (fumConfiable) {
            CalcularEGXFUMUseCase(
                CalculadoraEG(fum, calculadoraFechas = CalcDatesImpl())
            ).calcularEGXFUM() + " Sem" + " FUM confiable"
        } else {
            CalcularEGXUGUseCase(
                CalculadoraEG(
                    fug,
                    cantSemUg.toInt(),
                    cantDiasUg.toInt(),
                    CalcDatesImpl()
                )
            ).calcularEGXUG() + " Sem" + " FUM no confiable"
        }
    }

    private fun calcularIMC(peso: String, talla: String): String {
        val decimalFormat = DecimalFormat("#.00")
        return decimalFormat.format(
            peso.toDouble() /
                    ((talla.toDouble() / 100) * (talla.toDouble() / 100))
        ).toString()
    }

    private fun clasificacionIMC(imc: String): String {
        return when (imc.toDouble()) {


            in 0.0..18.8 -> "PESO DEFICIENTE"
            in 18.9..24.9 -> "NORMOPESO"
            in 25.0..29.9 -> "SOBREPESO"
            in 30.00..34.99 -> "OBESA CLASE I"
            in 35.00..39.99 -> "OBESA CLASE II"
            else -> "OBESA CLASE III"
        }
    }


    @SuppressLint("SetTextI18n")
    fun bind(binding: FragmentDetailBinding, gestante: Gestante) {
        binding.apply {
            try {
                if (gestante.foto != "") {
                    foto.setImageBitmap(getBitmapFromFile(binding.root.context, gestante.foto))
                } else {
                    foto.setImageResource(R.drawable.iconpregnantdefault)
                }
            } catch (e: Exception) {
                foto.setImageResource(R.drawable.iconpregnantdefault)
            }
            tvNombreApellidos.text = gestante.nombre + " " + gestante.apellidos

            tvEdad.text = if (gestante.edad != null) {
                gestante.edad + " A"
            } else ""
            tvEdadGestacionalHeader.text = if (gestante.fum != null || gestante.fug != null) {
                egGuia(
                    gestante.fumConfiable,
                    gestante.fum ?: "0/0/0",
                    gestante.fug ?: "0/0/0",
                    gestante.cantSemanasUG!!,
                    gestante.cantDiasUG!!
                )
            } else ""
            tvTelefono.text = if (gestante.telefono != null) if (gestante.telefono != "") {
                gestante.telefono
            } else {
                " Tel: Sin datos"
            }
            else ""
            tvEg.text = if (gestante.fum != null || gestante.fug != null) {
                if (gestante.fum != "0/0/0") {
                    "FUM: " + gestante.fum + " = " + CalcularEGXFUMUseCase(
                        CalculadoraEG(gestante.fum!!, calculadoraFechas = CalcDatesImpl())
                    ).calcularEGXFUM() + "\n"
                } else {
                    " "
                } +

                        if (gestante.fug != "0/0/0") {
                            "FUG: " + gestante.fug +
                                    " (${gestante.cantSemanasUG}.${gestante.cantDiasUG}s)" + " = " +
                                    CalcularEGXUGUseCase(
                                        CalculadoraEG(
                                            gestante.fug!!,
                                            gestante.cantSemanasUG!!.toInt(),
                                            gestante.cantDiasUG!!.toInt(),
                                            CalcDatesImpl()
                                        )
                                    ).calcularEGXUG()
                        } else ""
            } else ""

            tvImc.text = if (gestante.peso != null || gestante.talla != null) {
                if (gestante.peso != "" && gestante.talla != "") {
                    calcularIMC(gestante.peso!!, gestante.talla!!) + " " +
                            clasificacionIMC(calcularIMC(gestante.peso, gestante.talla))
                } else "Sin datos"
            } else ""

            tvFpp.text = if (gestante.fum != null || gestante.fug != null) {
                "FPP: " + CalculadoraFPPUseCase(CalculadoraEG(
                    if (gestante.fumConfiable) gestante.fum!! else gestante.fug!!,
                    gestante.cantSemanasUG!!.toInt(), gestante.cantDiasUG!!.toInt(),CalcDatesImpl()
                )).calcularFPP(if (gestante.fumConfiable) gestante.fum!! else gestante.fug!!)
            } else ""

            tvNotas.text = gestante.notas ?: ""

        }
    }

    fun eliminarGestante() {
        EliminarGestanteUseCase(repo)
            .eliminarGestanteUseCase(_gestante.value!!)
    }

    fun llamar(context: Context) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:${_gestante.value!!.telefono}")
        }
        context.startActivity(intent)
    }

    fun enviarSms(context: Context) {

        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("smsto:${_gestante.value!!.telefono}")
        }
        context.startActivity(intent)
    }

    fun passDataAndNavigateToEdicion(navController: NavController) {
        navController.navigate(
            GestanteDetailFragmentDirections.detailToEdicion(
                id = _gestante.value!!.id,
                nombre = _gestante.value!!.nombre,
                apellidos = _gestante.value!!.apellidos!!,
                edad = _gestante.value!!.edad!!,
                peso = _gestante.value!!.peso!!,
                talla = _gestante.value!!.talla!!,
                telefono = _gestante.value!!.telefono!!,
                fumConfiable = _gestante.value!!.fumConfiable,
                fum = _gestante.value!!.fum!!,
                fug = _gestante.value!!.fug!!,
                cantSemanas = _gestante.value!!.cantSemanasUG!!.toInt(),
                cantDias = _gestante.value!!.cantDiasUG!!.toInt(),
                foto = _gestante.value!!.foto
            )
        )
    }


}


class DetailViewModelFactory(
    private val repo: Repo
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repo) as T
        }
        throw IllegalArgumentException("No se corresponde con la clase viewModel esperada")
    }
}
package com.devj.gestantescontrol.framework.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.devj.gestantescontrol.domain.CalculadoraEG
import com.devj.gestantescontrol.domain.Gestante
import com.devj.gestantescontrol.domain.Repo
import com.devj.gestantescontrol.framework.CalcDatesImpl
import com.devj.gestantescontrol.framework.NumberAndEG
import com.devj.gestantescontrol.usescases.CalcularEGXFUMUseCase
import com.devj.gestantescontrol.usescases.CalcularEGXUGUseCase
import com.devj.gestantescontrol.usescases.GetAllGestantesUseCase
import kotlinx.coroutines.flow.Flow


class AjustesViewModel(private val repo: Repo) : ViewModel() {

    val gestantesList = getAllGestantes().asLiveData()

    fun getAllGestantes(): Flow<List<Gestante>> {
        return GetAllGestantesUseCase(repo).getAllGestantesUseCase()
    }


    fun getPhoneNumberAndEG(listGestante:List<Gestante>):List<NumberAndEG>{
        val lista:MutableList<NumberAndEG> = mutableListOf()
        for (gestante in listGestante){
         lista.add(   NumberAndEG(
                gestante.telefono?:"",
             if (gestante.fumConfiable){
                 CalcularEGXFUMUseCase(
                     CalculadoraEG(gestante.fum!!, calculadoraFechas = CalcDatesImpl())
                 ).calcularEGXFUM()
             }else{
                 CalcularEGXUGUseCase(
                     CalculadoraEG(gestante.fug!!,
                         gestante.cantSemanasUG!!.toInt(),
                         gestante.cantDiasUG!!.toInt(),
                         CalcDatesImpl())
                 ).calcularEGXUG()
             }
            )
         )
        }
        return lista
    }




}

class AjustesViewModelFactory(
    private val repo: Repo
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(AjustesViewModel::class.java)) {
            return AjustesViewModel(repo) as T
        }
        throw IllegalArgumentException("No se corresponde con la clase viewModel esperada")
    }
}
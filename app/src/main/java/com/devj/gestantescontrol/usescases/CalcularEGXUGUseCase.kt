package com.devj.gestantescontrol.usescases

import com.devj.gestantescontrol.domain.CalculadoraEG

class CalcularEGXUGUseCase(private val calculadoraEg: CalculadoraEG) {
    fun calcularEGXUG(): String{
       return calculadoraEg.calcularPorUSG()
    }
}
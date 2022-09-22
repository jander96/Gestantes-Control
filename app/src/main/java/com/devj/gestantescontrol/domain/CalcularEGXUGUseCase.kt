package com.devj.gestantescontrol.domain

import com.devj.gestantescontrol.data.model.CalculadoraEg

class CalcularEGXUGUseCase {
    fun calcularEGXUG(calculadoraEg: CalculadoraEg): String{
       return calculadoraEg.calcularPorUSG()
    }
}
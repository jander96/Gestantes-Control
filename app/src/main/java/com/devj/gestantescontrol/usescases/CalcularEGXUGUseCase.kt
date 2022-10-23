package com.devj.gestantescontrol.usescases

import com.devj.gestantescontrol.domain.CalculadoraEg

class CalcularEGXUGUseCase {
    fun calcularEGXUG(calculadoraEg: CalculadoraEg): String{
       return calculadoraEg.calcularPorUSG()
    }
}
package com.devj.gestantescontrol.usescases

import com.devj.gestantescontrol.domain.CalculadoraEg

class CalcularEGXFUMUseCase {

    fun calcularEGXFUM(calculadora: CalculadoraEg):String{
        return calculadora.calcularPorFUM()
    }
}
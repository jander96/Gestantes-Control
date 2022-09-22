package com.devj.gestantescontrol.domain

import com.devj.gestantescontrol.data.model.CalculadoraEg

class CalcularEGXFUMUseCase {

    fun calcularEGXFUM(calculadora: CalculadoraEg):String{
        return calculadora.calcularPorFUM()
    }
}
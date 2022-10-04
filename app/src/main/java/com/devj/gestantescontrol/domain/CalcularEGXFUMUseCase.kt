package com.devj.gestantescontrol.domain

class CalcularEGXFUMUseCase {

    fun calcularEGXFUM(calculadora: CalculadoraEg):String{
        return calculadora.calcularPorFUM()
    }
}
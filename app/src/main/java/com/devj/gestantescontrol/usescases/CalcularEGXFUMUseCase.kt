package com.devj.gestantescontrol.usescases


import com.devj.gestantescontrol.domain.CalculadoraEG


class CalcularEGXFUMUseCase(private val calculadoraEg: CalculadoraEG) {

     fun calcularEGXFUM():String{
       return calculadoraEg.calcularPorFUM()
    }
}
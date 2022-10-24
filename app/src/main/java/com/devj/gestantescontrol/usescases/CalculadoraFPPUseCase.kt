package com.devj.gestantescontrol.usescases

import com.devj.gestantescontrol.domain.CalculadoraEG

class CalculadoraFPPUseCase(private val calculadoraEg: CalculadoraEG) {

    fun calcularFPP(fum:String="0/0/0",fug:String="0/0/0"):String{
       return calculadoraEg.calcularFPP(fum, fug)
    }
}
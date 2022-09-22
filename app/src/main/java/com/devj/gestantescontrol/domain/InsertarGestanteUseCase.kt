package com.devj.gestantescontrol.domain

import android.content.Context
import com.devj.gestantescontrol.data.GestantesRepositorio
import com.devj.gestantescontrol.data.database.entities.GestanteEntity

class InsertarGestanteUseCase(contexto : Context) {
    val repositorio = GestantesRepositorio(contexto)
    fun insertarGestanteUseCase(gestante: GestanteEntity){
        repositorio.insertarGestante(gestante)
    }
}
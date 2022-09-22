package com.devj.gestantescontrol.domain

import android.content.Context
import com.devj.gestantescontrol.data.GestantesRepositorio
import com.devj.gestantescontrol.data.database.entities.GestanteEntity

class ActualizarGestanteUseCase(applicationContext: Context) {
    private val repositorio = GestantesRepositorio(applicationContext)

    suspend fun actualizarGestante(gestante: GestanteEntity){
        repositorio.actualizarGestante(gestante)
    }
}
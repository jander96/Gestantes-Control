package com.devj.gestantescontrol.domain

import android.content.Context
import com.devj.gestantescontrol.data.GestantesRepositorio
import com.devj.gestantescontrol.data.database.entities.GestanteEntity

class EliminarGestanteUseCase(val applicationContext: Context) {
    val repositorio= GestantesRepositorio(applicationContext)
    fun eliminarGestanteUseCase(gestante: GestanteEntity){
        repositorio.eliminarGestante(gestante)

    }
}
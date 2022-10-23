package com.devj.gestantescontrol.usescases

import android.content.Context
import com.devj.gestantescontrol.data.GestantesRepositorio
import com.devj.gestantescontrol.domain.Gestante
import com.devj.gestantescontrol.domain.Repo
import com.devj.gestantescontrol.framework.roomdatabase.entities.GestanteEntity

class EliminarGestanteUseCase(private val repositorio : Repo) {
    fun eliminarGestanteUseCase(gestante: Gestante){
        repositorio.borrarGestante(gestante)
    }
}
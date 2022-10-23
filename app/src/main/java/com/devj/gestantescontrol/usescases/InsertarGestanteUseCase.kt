package com.devj.gestantescontrol.usescases

import com.devj.gestantescontrol.domain.Gestante
import com.devj.gestantescontrol.domain.Repo


class InsertarGestanteUseCase(private val repositorio : Repo) {
    fun insertarGestanteUseCase(gestante: Gestante){
        repositorio.insertarGestante(gestante)
    }
}
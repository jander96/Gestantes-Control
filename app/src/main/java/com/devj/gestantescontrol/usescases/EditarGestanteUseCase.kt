package com.devj.gestantescontrol.usescases


import com.devj.gestantescontrol.domain.Gestante
import com.devj.gestantescontrol.domain.Repo

class EditarGestanteUseCase(private val repositorio :Repo) {

    suspend fun editarGestante(gestante: Gestante){
        repositorio.editarGestante(gestante)
    }
}
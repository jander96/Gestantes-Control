package com.devj.gestantescontrol.domain


import kotlinx.coroutines.flow.Flow

interface Repo {

    fun getAllGestantes(): Flow<List<Gestante>>

    suspend fun editarGestante(gestante: Gestante)

    fun insertarGestante(gestante: Gestante)

    fun borrarGestante(gestanteEntity: Gestante)
}
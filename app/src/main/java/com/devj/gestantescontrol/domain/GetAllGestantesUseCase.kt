package com.devj.gestantescontrol.domain

import android.content.Context
import com.devj.gestantescontrol.data.GestantesRepositorio
import com.devj.gestantescontrol.data.database.entities.GestanteEntity
import kotlinx.coroutines.flow.Flow

class GetAllGestantesUseCase(context: Context) {
    private val repositorio = GestantesRepositorio(context)

    fun getAllGestantesUseCase(): Flow<List<GestanteEntity>> {
        return repositorio.getAllGestantes()
    }
}
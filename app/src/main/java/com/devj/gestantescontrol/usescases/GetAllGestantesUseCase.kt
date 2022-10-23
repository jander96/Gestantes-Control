package com.devj.gestantescontrol.usescases

import com.devj.gestantescontrol.domain.Gestante
import com.devj.gestantescontrol.domain.Repo
import kotlinx.coroutines.flow.Flow

class GetAllGestantesUseCase(private val repositorio : Repo) {
    fun getAllGestantesUseCase(): Flow<List<Gestante>> {
        return repositorio.getAllGestantes()
    }
}
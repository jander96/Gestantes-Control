package com.devj.gestantescontrol.domain

import kotlinx.coroutines.flow.Flow

class RepoImpl(private val localDataBase : LocalDataBase):Repo {
    override fun getAllGestantes(): Flow<List<Gestante>> {
      return  localDataBase.getAllGestantes()
    }

    override suspend fun editarGestante(gestante: Gestante) {
        return localDataBase.editarGestante(gestante)
    }

    override fun insertarGestante(gestante: Gestante) {
        return localDataBase.insertarGestante(gestante)
    }

    override fun borrarGestante(gestante: Gestante) {
        return localDataBase.borrarGestante(gestante)
    }
}
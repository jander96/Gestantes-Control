package com.devj.gestantescontrol.framework.roomdatabase

import com.devj.gestantescontrol.domain.Gestante
import com.devj.gestantescontrol.domain.LocalDataBase
import com.devj.gestantescontrol.domain.toGestante
import com.devj.gestantescontrol.domain.toGestanteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalDataBaseImpl(private val roomDataBase : AppGestanteDatabase): LocalDataBase {
    override fun getAllGestantes(): Flow<List<Gestante>> {
      return roomDataBase.getGestanteDao().getAllGestantes().map {
          it.map {
              it.toGestante()
          }
      }
    }

    override suspend fun editarGestante(gestante: Gestante) {
        roomDataBase.getGestanteDao().editarGestante(gestante.toGestanteEntity())
    }

    override fun insertarGestante(gestante: Gestante) {
        roomDataBase.getGestanteDao().insertarGestante(gestante.toGestanteEntity())
    }

    override fun borrarGestante(gestante: Gestante) {
        roomDataBase.getGestanteDao().borrarGestante(gestante.toGestanteEntity())
    }

}
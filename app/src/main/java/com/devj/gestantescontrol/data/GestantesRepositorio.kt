package com.devj.gestantescontrol.data

import android.content.Context
import com.devj.gestantescontrol.GestantesApplication
import com.devj.gestantescontrol.framework.roomdatabase.entities.GestanteEntity
import kotlinx.coroutines.flow.Flow


class GestantesRepositorio(applicationContext: Context) {

    private val gestanteDao = (applicationContext as GestantesApplication).database.getGestanteDao()


    suspend fun actualizarGestante(gestante: GestanteEntity){
        gestanteDao.editarGestante(gestante)
    }
    fun insertarGestante(gestante: GestanteEntity){
        gestanteDao.insertarGestante(gestante)
    }
    fun getAllGestantes(): Flow<List<GestanteEntity>> {
        return gestanteDao.getAllGestantes()
    }
    fun eliminarGestante(gestante: GestanteEntity){
        gestanteDao.borrarGestante(gestante)
    }



}
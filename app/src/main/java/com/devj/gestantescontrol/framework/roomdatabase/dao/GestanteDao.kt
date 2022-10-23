package com.devj.gestantescontrol.framework.roomdatabase.dao

import androidx.room.*
import com.devj.gestantescontrol.framework.roomdatabase.entities.GestanteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GestanteDao {

    @Query("SELECT * FROM gestante_table")
    fun getAllGestantes(): Flow<List<GestanteEntity>>

    @Update
    suspend fun editarGestante(gestante: GestanteEntity)

    @Insert(onConflict =OnConflictStrategy.REPLACE )
    fun insertarGestante(gestante: GestanteEntity)

    @Delete
    fun borrarGestante(gestanteEntity: GestanteEntity)

}
package com.devj.gestantescontrol.data.cache.dao

import androidx.room.*
import com.devj.gestantescontrol.data.cache.model.PregnantEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PregnantDao {

    @Query("SELECT * FROM pregnant_table")
    fun getAllPregnant(): Flow<List<PregnantEntity>>

    @Insert(onConflict =OnConflictStrategy.REPLACE )
    fun insertPregnant(pregnant: PregnantEntity)

    @Delete
    fun deletePregnant(pregnant: PregnantEntity)

}
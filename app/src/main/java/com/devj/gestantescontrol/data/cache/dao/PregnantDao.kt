package com.devj.gestantescontrol.data.cache.dao

import androidx.room.*
import com.devj.gestantescontrol.data.cache.model.PregnantEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PregnantDao {

    @Query("SELECT * FROM pregnant_table")
    fun getAllPregnant(): Flow<List<PregnantEntity>>

    @Query("SELECT * FROM pregnant_table WHERE id = :pregnantId")
    suspend fun getPregnantById(pregnantId: Int): PregnantEntity

    @Insert(onConflict =OnConflictStrategy.REPLACE )
    suspend fun insertPregnant(pregnant: PregnantEntity)

    @Delete
    suspend fun deletePregnant(pregnant: PregnantEntity)

    @Query("DELETE From pregnant_table Where id == :pregnantId")
    suspend fun deletePregnantById(pregnantId: Int)

}
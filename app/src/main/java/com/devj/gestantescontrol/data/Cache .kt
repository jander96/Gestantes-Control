package com.devj.gestantescontrol.data

import com.devj.gestantescontrol.data.cache.model.PregnantEntity
import kotlinx.coroutines.flow.Flow

interface Cache {
    fun getAllPregnant(): Flow<List<PregnantEntity>>

    suspend fun getPregnantById(pregnantId: Int): PregnantEntity

    suspend fun insertPregnant(pregnant: PregnantEntity)

    suspend fun deletePregnant(pregnant: PregnantEntity)

    suspend fun deletePregnantById(pregnantId: Int)
}

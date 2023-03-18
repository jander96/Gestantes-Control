package com.devj.gestantescontrol.data

import com.devj.gestantescontrol.data.cache.model.PregnantEntity
import kotlinx.coroutines.flow.Flow

interface Cache {
    fun getAllPregnant(): Flow<List<PregnantEntity>>

    fun insertPregnant(pregnant: PregnantEntity)

    fun deletePregnant(pregnant: PregnantEntity)
}

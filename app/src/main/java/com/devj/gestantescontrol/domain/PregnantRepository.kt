package com.devj.gestantescontrol.domain


import com.devj.gestantescontrol.data.cache.model.PregnantEntity
import com.devj.gestantescontrol.domain.model.Pregnant
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface PregnantRepository {
    fun getAllPregnant() : Flow<List<Pregnant>>
    suspend fun getPregnantById(pregnantId: Int): Pregnant

    suspend fun insertPregnant(pregnant: Pregnant)

    suspend fun deletePregnant(pregnant: Pregnant)

    suspend fun deletePregnantById(pregnantId: Int)
}
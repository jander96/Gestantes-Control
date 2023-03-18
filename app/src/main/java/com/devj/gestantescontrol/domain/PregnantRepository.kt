package com.devj.gestantescontrol.domain


import com.devj.gestantescontrol.domain.model.Pregnant
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface PregnantRepository {
    fun getAllPregnant() : Flow<List<Pregnant>>

    suspend fun insertPregnant(pregnant: Pregnant)

    suspend fun deletePregnant(pregnant: Pregnant)
}
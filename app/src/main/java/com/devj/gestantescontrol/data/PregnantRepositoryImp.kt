package com.devj.gestantescontrol.data

import com.devj.gestantescontrol.domain.PregnantRepository
import com.devj.gestantescontrol.domain.model.Pregnant
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class PregnantRepositoryImp @Inject constructor( private val cache: Cache) : PregnantRepository {
    override fun getAllPregnant(): Flow<List<Pregnant>> {
        TODO()
    }

    override suspend fun insertPregnant(pregnant: Pregnant) {
        TODO()
    }

    override suspend fun deletePregnant(pregnant: Pregnant) {
        TODO()
    }

}



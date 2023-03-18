package com.devj.gestantescontrol.data

import com.devj.gestantescontrol.data.cache.model.PregnantEntity
import com.devj.gestantescontrol.data.cache.model.mapper.PregnantEntityMapper
import com.devj.gestantescontrol.domain.PregnantRepository
import com.devj.gestantescontrol.domain.model.Pregnant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PregnantRepositoryImp @Inject constructor(
    private val cache: Cache,
    private val cacheMapper: PregnantEntityMapper
) : PregnantRepository {
    override fun getAllPregnant(): Flow<List<Pregnant>> {
        return cache.getAllPregnant().map { list ->
            list.map {
                cacheMapper.mapToDomain(it)
            }
        }
    }

    override suspend fun insertPregnant(pregnant: Pregnant) {
        cache.insertPregnant(PregnantEntity.fromDomain(pregnant))
    }

    override suspend fun deletePregnant(pregnant: Pregnant) {
        cache.deletePregnant(PregnantEntity.fromDomain(pregnant))
    }

}



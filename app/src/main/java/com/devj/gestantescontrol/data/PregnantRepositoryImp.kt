package com.devj.gestantescontrol.data

import android.util.Log
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

    override suspend fun getPregnantById(pregnantId: Int): Pregnant {
        val pregnantEntity = cache.getPregnantById(pregnantId)
        Log.d("EditIntent","Se ")
        return cacheMapper.mapToDomain(pregnantEntity)
    }

    override suspend fun insertPregnant(pregnant: Pregnant) {
        Log.d("Insert","Se mando insertion al cache")
        cache.insertPregnant(PregnantEntity.fromDomain(pregnant))
    }

    override suspend fun deletePregnant(pregnant: Pregnant) {
        cache.deletePregnant(PregnantEntity.fromDomain(pregnant))
    }

    override suspend fun deletePregnantById(pregnantId: Int) {
        cache.deletePregnantById(pregnantId)
    }

}



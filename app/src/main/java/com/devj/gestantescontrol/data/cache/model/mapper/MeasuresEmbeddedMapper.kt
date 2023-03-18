package com.devj.gestantescontrol.data.cache.model.mapper

import com.devj.gestantescontrol.data.cache.model.MeasuresEmbedded
import com.devj.gestantescontrol.domain.model.Measures
import javax.inject.Inject

class MeasuresEmbeddedMapper @Inject constructor(): CacheMapper<MeasuresEmbedded,Measures> {
    override fun mapToDomain(cacheEntity: MeasuresEmbedded) =
        Measures(cacheEntity.weight,cacheEntity.size)
}
package com.devj.gestantescontrol.data.cache.model.mapper

import com.devj.gestantescontrol.data.cache.model.RiskFactorEmbedded
import com.devj.gestantescontrol.domain.model.RiskFactor
import javax.inject.Inject

class RiskFactorEmbeddedMapper @Inject constructor(): CacheMapper<RiskFactorEmbedded,RiskFactor> {
    override fun mapToDomain(cacheEntity: RiskFactorEmbedded)=  RiskFactor(cacheEntity.name)
}
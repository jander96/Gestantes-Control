package com.devj.gestantescontrol.data.cache.model.mapper

import com.devj.gestantescontrol.data.cache.model.PregnantEntity
import com.devj.gestantescontrol.domain.model.Pregnant
import javax.inject.Inject

class PregnantEntityMapper @Inject constructor(
    private val measuresMapper: MeasuresEmbeddedMapper,
    private val dataDateEmbeddedMapper: DataDateEmbeddedMapper,
    private val riskFactorEmbeddedMapper: RiskFactorEmbeddedMapper
    ): CacheMapper<PregnantEntity,Pregnant> {
    override fun mapToDomain(cacheEntity: PregnantEntity): Pregnant {
        return Pregnant(
            id = cacheEntity.id,
            name = cacheEntity.name,
            lastName = cacheEntity.lastName,
            age = cacheEntity.age,
            phoneNumber = cacheEntity.phoneNumber,
            measures = measuresMapper.mapToDomain(cacheEntity.measures),
            dataDate = dataDateEmbeddedMapper.mapToDomain(cacheEntity.dataDate),
            riskFactors = cacheEntity.riskFactors.map { riskFactorEmbeddedMapper.mapToDomain(it) },
            notes = cacheEntity.notes,
            photo = cacheEntity.photo
        )
    }
}
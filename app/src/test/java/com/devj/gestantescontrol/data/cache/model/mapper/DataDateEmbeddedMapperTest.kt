package com.devj.gestantescontrol.data.cache.model.mapper

import com.devj.gestantescontrol.data.cache.model.PregnantEntity
import com.devj.gestantescontrol.di.ProviderForTest
import com.devj.gestantescontrol.domain.model.Pregnant
import org.junit.Before
import org.junit.Test

class DataDateEmbeddedMapperTest {
    private lateinit var pregnantEntity: PregnantEntity
    private lateinit var pregnant: Pregnant
    private lateinit var pregnantEntityMapper: PregnantEntityMapper

    @Before
    fun setup() {
        pregnantEntity = ProviderForTest.pregnantEntity1
        pregnant = ProviderForTest.domainPregnant1
        pregnantEntityMapper = PregnantEntityMapper(
            MeasuresEmbeddedMapper(),
            DataDateEmbeddedMapper(),
            RiskFactorEmbeddedMapper()
        )
    }

    @Test
    fun `map entity-domain is working ok`() {
        assert(PregnantEntity.fromDomain(pregnant) == pregnantEntity)
        assert(pregnantEntityMapper.mapToDomain(pregnantEntity) == pregnant)
    }
}
package com.devj.gestantescontrol

import com.devj.gestantescontrol.data.cache.model.DataDateEmbedded
import com.devj.gestantescontrol.data.cache.model.MeasuresEmbedded
import com.devj.gestantescontrol.data.cache.model.PregnantEntity
import com.devj.gestantescontrol.data.cache.model.RiskFactorEmbedded
import com.devj.gestantescontrol.domain.model.DataDate
import com.devj.gestantescontrol.domain.model.Measures
import com.devj.gestantescontrol.domain.model.Pregnant
import com.devj.gestantescontrol.domain.model.RiskFactor

object ProviderForAndroidTest {
    private val measures = Measures(60.0,170.0)
    private val dataDate =  DataDate(
        "01/01/2020",
        true,
        "",
        12,
        2,
        "",
        12,
        34,
        "",
        23,
        56)
    private val measuresEmbedded = MeasuresEmbedded(60.0,170.0)
    private val dataDateEmbedded =  DataDateEmbedded(
        "01/01/2020",
        true,
        "",
        12,
        2,
        "",
        12,
        34,
        "",
        23,
        56)
    val  domainPregnant1 = Pregnant(
        id = 1,
        name = "Mary",
        lastName = "Lee",
        age = 23,
        phoneNumber = "12345678",
        measures = measures,
        dataDate = dataDate,
        riskFactors = listOf(RiskFactor("Smoker")),
        notes = "nota",
        photo = "photo"
    )
    val  domainPregnant2 = Pregnant(
        id = 2,
        name = "Mary",
        lastName = "Lee",
        age = 23,
        phoneNumber = "12345678",
        measures = measures,
        dataDate = dataDate,
        riskFactors = listOf(RiskFactor("Smoker")),
        notes = "nota",
        photo = "photo"
    )


    val  pregnantEntity1 = PregnantEntity(
        id = 1,
        name = "Mary",
        lastName = "Lee",
        age = 23,
        phoneNumber = "12345678",
        measures = measuresEmbedded,
        dataDate = dataDateEmbedded,
        riskFactors = listOf(RiskFactorEmbedded("Smoker")),
        notes = "nota",
        photo = "photo"
    )
    val  pregnantEntity2 = PregnantEntity(
        id = 2,
        name = "Mary",
        lastName = "Lee",
        age = 23,
        phoneNumber = "12345678",
        measures = measuresEmbedded,
        dataDate = dataDateEmbedded,
        riskFactors = listOf(RiskFactorEmbedded("Smoker")),
        notes = "nota",
        photo = "photo"
    )
}
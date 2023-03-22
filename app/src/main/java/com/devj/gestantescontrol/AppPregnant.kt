package com.devj.gestantescontrol

import android.app.Application
import com.devj.gestantescontrol.data.cache.PregnantDatabase
import com.devj.gestantescontrol.data.cache.dao.PregnantDao
import com.devj.gestantescontrol.data.cache.model.DataDateEmbedded
import com.devj.gestantescontrol.data.cache.model.MeasuresEmbedded
import com.devj.gestantescontrol.data.cache.model.PregnantEntity
import com.devj.gestantescontrol.data.cache.model.RiskFactorEmbedded
import com.devj.gestantescontrol.domain.model.DataDate
import com.devj.gestantescontrol.domain.model.Measures
import com.devj.gestantescontrol.domain.model.Pregnant
import com.devj.gestantescontrol.domain.model.RiskFactor
import com.devj.gestantescontrol.domain.usescases.pregnantListMock
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltAndroidApp
class AppPregnant:Application(){
    @Inject
    lateinit var dao : PregnantDao
    override fun onCreate() = runBlocking {
        super.onCreate()

        dao.insertPregnant(PregnantEntity.fromDomain(pregnantListMock[0]))

    }
}
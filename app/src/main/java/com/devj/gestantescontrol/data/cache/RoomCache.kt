package com.devj.gestantescontrol.data.cache

import android.util.Log
import com.devj.gestantescontrol.data.Cache
import com.devj.gestantescontrol.data.cache.dao.PregnantDao
import com.devj.gestantescontrol.data.cache.model.PregnantEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class RoomCache @Inject constructor(private val pregnantDao: PregnantDao): Cache {
    override fun getAllPregnant(): Flow<List<PregnantEntity>> {
        return pregnantDao.getAllPregnant()
    }
    override suspend fun getPregnantById(pregnantId: Int): PregnantEntity {
        return pregnantDao.getPregnantById(pregnantId)
    }

    override suspend fun insertPregnant(pregnant: PregnantEntity) {
        pregnantDao.insertPregnant(pregnant)
    }

    override suspend fun deletePregnant(pregnant: PregnantEntity) {
        pregnantDao.deletePregnant(pregnant)
    }

    override suspend fun deletePregnantById(pregnantId: Int) {
        pregnantDao.deletePregnantById(pregnantId)
        Log.d("FlowIntent","Se ejecuto la query")
    }
}
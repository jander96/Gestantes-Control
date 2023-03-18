package com.devj.gestantescontrol.data.cache

import com.devj.gestantescontrol.data.Cache
import com.devj.gestantescontrol.data.cache.dao.PregnantDao
import com.devj.gestantescontrol.data.cache.model.PregnantEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class RoomCache @Inject constructor(private val pregnantDao: PregnantDao): Cache {
    override fun getAllPregnant(): Flow<List<PregnantEntity>> {
        return pregnantDao.getAllPregnant()
    }

    override fun insertPregnant(pregnant: PregnantEntity) {
        pregnantDao.insertPregnant(pregnant)
    }

    override fun deletePregnant(pregnant: PregnantEntity) {
        pregnantDao.deletePregnant(pregnant)
    }
}
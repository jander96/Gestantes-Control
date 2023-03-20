package com.devj.gestantescontrol.data.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.devj.gestantescontrol.ProviderForAndroidTest
import com.devj.gestantescontrol.data.cache.PregnantDatabase
import com.devj.gestantescontrol.data.cache.dao.PregnantDao
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class PregnantDaoTest {
    private lateinit var pregnantDao: PregnantDao
    private lateinit var pregnantDatabase: PregnantDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        pregnantDatabase =
            Room.inMemoryDatabaseBuilder(context, PregnantDatabase::class.java).build()
        pregnantDao = pregnantDatabase.getPregnantDao()
    }

    @After
    fun cleanup() {
        pregnantDatabase.close()
    }


    @Test
    fun database_is_returning_the_object_which_are_added() = runBlocking {
        pregnantDao.insertPregnant(ProviderForAndroidTest.pregnantEntity1)
        pregnantDao.insertPregnant(ProviderForAndroidTest.pregnantEntity2)

        assert(pregnantDao.getAllPregnant().last().contains(ProviderForAndroidTest.pregnantEntity1))
        assert(pregnantDao.getAllPregnant().last().contains(ProviderForAndroidTest.pregnantEntity2))

    }


}

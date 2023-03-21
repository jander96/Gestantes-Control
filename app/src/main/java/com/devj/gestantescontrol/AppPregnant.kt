package com.devj.gestantescontrol

import android.app.Application
import com.devj.gestantescontrol.data.cache.PregnantDatabase
import com.devj.gestantescontrol.data.cache.dao.PregnantDao
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class AppPregnant:Application(){
    @Inject
    lateinit var dao : PregnantDao
    override fun onCreate() {
        super.onCreate()
        dao.getAllPregnant()
    }
}
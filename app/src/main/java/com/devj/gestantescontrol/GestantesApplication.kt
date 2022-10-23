package com.devj.gestantescontrol

import android.app.Application
import com.devj.gestantescontrol.framework.roomdatabase.AppGestanteDatabase

class GestantesApplication:Application() {
    val database: AppGestanteDatabase by lazy {
        AppGestanteDatabase.getDatabase(this)
    }
}
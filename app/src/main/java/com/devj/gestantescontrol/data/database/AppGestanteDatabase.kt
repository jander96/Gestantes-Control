package com.devj.gestantescontrol.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.devj.gestantescontrol.data.database.converters.Converter
import com.devj.gestantescontrol.data.database.dao.GestanteDao
import com.devj.gestantescontrol.data.database.entities.GestanteEntity

@Database(entities = [GestanteEntity::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppGestanteDatabase:RoomDatabase() {
    abstract fun getGestanteDao(): GestanteDao
    companion object{
        @Volatile
        private var INSTANCE : AppGestanteDatabase? = null
        fun getDatabase(context: Context):AppGestanteDatabase{

            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    AppGestanteDatabase::class.java,
                    "gestantes_database"
                ).build()
                INSTANCE=instance
                instance
            }
        }
    }
}
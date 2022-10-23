package com.devj.gestantescontrol.framework.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.devj.gestantescontrol.framework.roomdatabase.converters.Converter
import com.devj.gestantescontrol.framework.roomdatabase.dao.GestanteDao
import com.devj.gestantescontrol.framework.roomdatabase.entities.GestanteEntity

@Database(entities = [GestanteEntity::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppGestanteDatabase:RoomDatabase() {
    abstract fun getGestanteDao(): GestanteDao
    companion object{
        @Volatile
        private var INSTANCE : AppGestanteDatabase? = null
        fun getDatabase(context: Context): AppGestanteDatabase {

            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    AppGestanteDatabase::class.java,
                    "gestantes_database"
                ).build()
                INSTANCE =instance
                instance
            }
        }
    }
}
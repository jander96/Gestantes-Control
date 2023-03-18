package com.devj.gestantescontrol.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.devj.gestantescontrol.data.cache.converters.Converter
import com.devj.gestantescontrol.data.cache.dao.PregnantDao
import com.devj.gestantescontrol.data.cache.model.PregnantEntity

@Database(entities = [PregnantEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class PregnantDatabase:RoomDatabase() {
    abstract fun getPregnantDao(): PregnantDao

}
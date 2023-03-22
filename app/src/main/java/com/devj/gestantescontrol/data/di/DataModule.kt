package com.devj.gestantescontrol.data.di


import android.content.Context
import androidx.room.Room
import com.devj.gestantescontrol.data.cache.PregnantDatabase
import com.devj.gestantescontrol.data.cache.dao.PregnantDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PregnantDatabase{
        return Room.databaseBuilder(
            context,
            PregnantDatabase::class.java,
            "pregnant_database"
        ).build()
    }
    @Provides
    fun provideDao(database: PregnantDatabase):PregnantDao{
        return database.getPregnantDao()
    }

}
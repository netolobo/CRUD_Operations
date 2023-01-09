package com.netoloboapps.crudoperations.data.di

import android.content.Context
import androidx.room.Room
import com.netoloboapps.crudoperations.data.local.MotorcycleDao
import com.netoloboapps.crudoperations.data.local.MotorcycleDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MotorcycleModule {

    @Provides
    fun providesRoomDao(database: MotorcycleDb): MotorcycleDao {
        return database.dao
    }

    @Singleton
    @Provides
    fun provideRoomDatabase(
        @ApplicationContext
        appContext: Context
    ): MotorcycleDb {
        return Room.databaseBuilder(
            appContext,
            MotorcycleDb::class.java,
            "mortorcycle_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

}
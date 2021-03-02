package com.example.myweather.data.local

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CityModule {

    @Singleton
    @Provides
    fun provideCityDatabase(@ApplicationContext context: Context)
    = Room.databaseBuilder(
            context.applicationContext,
            CityDatabase::class.java,
            "cityData-db"
    ).build()

    @Singleton
    @Provides
    fun provideCityDao(cityDatabase: CityDatabase) = cityDatabase.cityDao()

}
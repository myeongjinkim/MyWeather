package com.example.myweather.data.remote

import android.content.Context
import androidx.room.Room
import com.example.myweather.data.local.CityDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherModule {

    @Singleton
    @Provides
    fun provideWeatherApi() = Service().build("https://api.openweathermap.org").create(WeatherApi::class.java)
}
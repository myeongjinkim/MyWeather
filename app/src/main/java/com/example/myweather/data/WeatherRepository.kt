package com.example.myweather.data

import android.app.Application
import com.example.myweather.data.local.CityDao
import com.example.myweather.data.local.CityDatabase
import com.example.myweather.data.remote.WeatherService

class WeatherRepository {   //suspend 한정자

    private var cityDao: CityDao
    constructor(application: Application) {
        var cityDatabase = CityDatabase.getInstance(application)
        cityDao = cityDatabase!!.cityDao()
    }
    fun getCityLocalDataSource()= cityDao

    private var weatherClient = WeatherService.client
    fun getWeatherRemoteDataSource(lat:Double, lon:Double, myKey:String, lang:String, units:String) = weatherClient.getWeather(lat, lon, myKey, lang, units)
}
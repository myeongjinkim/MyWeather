package com.example.myweather.data

import android.app.Application
import com.example.myweather.data.local.CityDao
import com.example.myweather.data.local.CityDatabase
import com.example.myweather.data.remote.WeatherService

class CityRepository {
    private var cityDao: CityDao
    constructor(application: Application) {
        var cityDatabase = CityDatabase.getInstance(application)
        cityDao = cityDatabase!!.cityDao()
    }
    fun getCityLocalDataSource()= cityDao
}
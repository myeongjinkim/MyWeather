package com.example.myweather

import android.app.Application

class WeatherRepository {   //suspend 한정자

    private var weatherClient = WeatherService.client
    private var cityDao:CityDao
    constructor(application: Application) {
        var cityDatabase = CityDatabase.getInstance(application)
        cityDao = cityDatabase!!.cityDao()
    }
    fun getRepositories(lat:Double, lon:Double, myKey:String, lang:String, units:String) = weatherClient.getWeather(lat, lon, myKey, lang, units)
    fun getCityDao()= cityDao
}
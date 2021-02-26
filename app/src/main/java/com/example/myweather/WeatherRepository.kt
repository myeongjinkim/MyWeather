package com.example.myweather

import android.app.Application

class WeatherRepository {   //suspend 한정자

    private var weatherClient = WeatherService.client

    fun getRepositories(lat:Double, lon:Double, myKey:String, lang:String, units:String) = weatherClient.getWeather(lat, lon, myKey, lang, units)
    fun getCity(application: Application):City{
        var cityDatabase = CityDatabase.getInstance(application)
        return cityDatabase.cityDao().getAll()[0]
    }

}
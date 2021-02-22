package com.example.myweather

class WeatherRepository {   //suspend 한정자
    private var weatherClient = WeatherService.client
    fun getRepositories(lat:Double, lon:Double, myKey:String, lang:String, units:String) = weatherClient.getWeather(lat, lon, myKey, lang, units)

}
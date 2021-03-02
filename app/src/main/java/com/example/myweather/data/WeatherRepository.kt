package com.example.myweather.data

import com.example.myweather.data.local.CityDao
import com.example.myweather.data.remote.WeatherApi
import javax.inject.Inject

class WeatherRepository@Inject constructor(
    private var cityDao: CityDao,
    private var weatherApi: WeatherApi
) {

    fun getCityLocalDataSource()= cityDao
    fun getWeatherRemoteDataSource(lat:Double, lon:Double, myKey:String, lang:String, units:String) = weatherApi.getWeather(lat, lon, myKey, lang, units)

}
package com.example.myweather.data.remote

object WeatherService {
    private const val WEATHER_URL = "https://api.openweathermap.org"
    var client = Service().build(WEATHER_URL).create(WeatherApi::class.java)
}
package com.example.myweather

object WeatherService {
    private const val WEATHER_URL = "https://api.openweathermap.org"
    var client = Service().build(WEATHER_URL).create(MyApi::class.java)
}
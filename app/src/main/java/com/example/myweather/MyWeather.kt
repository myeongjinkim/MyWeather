package com.example.myweather

class MyWeather{
    lateinit var current:WeatherInfo
}
class WeatherInfo{
    var temp:Double = 0.0
    var feels_like:Double = 0.0
    lateinit var weather:List<Weather>
}
class Weather {
    var description:String = ""
    var icon:String = ""
}
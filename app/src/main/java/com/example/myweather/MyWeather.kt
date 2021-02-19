package com.example.myweather

class MyWeather{
    lateinit var weather:List<Weather>
    lateinit var main:Main
}
class Weather {
    var description:String = ""
}
class Main {
    var temp:Double = 0.0
    var feels_like:Double = 0.0
    var temp_min:Double = 0.0
    var temp_max:Double = 0.0
}
package com.example.myweather.data.remote

import com.google.gson.annotations.SerializedName

data class MyWeather(
        @SerializedName("current")
        var current: MyWeatherCurrent
)
data class MyWeatherCurrent(
        @SerializedName("temp")
        var temp:Double,
        @SerializedName("feels_like")
        var feels_like:Double,
        @SerializedName("weather")
        var weather:List<MyWeatherDescription>
)
data class MyWeatherDescription (
        @SerializedName("description")
        var description:String,
        @SerializedName("icon")
        var icon:String
)



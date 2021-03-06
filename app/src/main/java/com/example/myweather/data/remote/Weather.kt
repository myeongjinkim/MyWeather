package com.example.myweather.data.remote

import com.google.gson.annotations.SerializedName

data class MyWeather(
        @SerializedName("timezone_offset")
        var timezone_offset:Int,
        @SerializedName("current")
        var current: MyWeatherCurrent,
        @SerializedName("hourly")
        var hourly:List<MyWeatherHourly>,
        @SerializedName("daily")
        var daily: List<MyWeatherDaily>
)
data class MyWeatherCurrent(
        @SerializedName("temp")
        var temp:Double,
        @SerializedName("feels_like")
        var feels_like:Double,
        @SerializedName("weather")
        var weather:List<MyWeatherDescription>
)

data class MyWeatherHourly(
        @SerializedName("dt")
        var dt:Int,
        @SerializedName("temp")
        var temp:Double,
        @SerializedName("feels_like")
        var feels_like:Double,
        @SerializedName("weather")
        var weather:List<MyWeatherDescription>,
        @SerializedName("pop")
        var pop:Double
)

data class MyWeatherDaily(
        @SerializedName("dt")
        var dt:Int,
        @SerializedName("temp")
        var temp:MyWeatherDailyTemp,
        @SerializedName("weather")
        var weather:List<MyWeatherDescription>,
        @SerializedName("pop")
        var pop:Double
)

data class MyWeatherDescription (
        @SerializedName("description")
        var description:String,
        @SerializedName("icon")
        var icon:String
)

data class MyWeatherDailyTemp(
        @SerializedName("min")
        var min:Double,
        @SerializedName("max")
        var max:Double
)



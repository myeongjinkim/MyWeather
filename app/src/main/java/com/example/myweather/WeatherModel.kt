package com.example.myweather

import com.google.gson.annotations.SerializedName

data class MyWeatherRepositoryModel(
        @SerializedName("current")
        var current:WeatherInfoRepositoryModel
)
data class WeatherInfoRepositoryModel(
        @SerializedName("temp")
        var temp:Double,
        @SerializedName("feels_like")
        var feels_like:Double,
        @SerializedName("weather")
        var weather:List<WeatherRepositoryModel>
)
data class WeatherRepositoryModel (
        @SerializedName("description")
        var description:String,
        @SerializedName("icon")
        var icon:String
)



package com.example.myweather

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MyApi {
    @GET("/data/2.5/weather")
    fun getWeather(@Query("q") myCity: String, @Query("appid") myKey: String): Call<MyWeather>

}
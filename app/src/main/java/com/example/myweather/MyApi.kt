package com.example.myweather

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {
    @GET("/data/2.5/onecall")
    fun getWeather(
            @Query("lat") mylat: Double,
            @Query("lon") mylon: Double,
            @Query("appid") myKey: String,
            @Query("lang") lang: String,
            @Query("units") units: String
    ): Observable<MyWeather>

}
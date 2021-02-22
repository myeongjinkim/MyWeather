package com.example.myweather

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {
    @GET("/data/2.5/onecall")
    fun getWeather(
            @Query("lat") lat: Double,
            @Query("lon") lon: Double,
            @Query("appid") key: String,
            @Query("lang") lang: String,
            @Query("units") units: String
    ): Observable<MyWeatherRepositoryModel>

}
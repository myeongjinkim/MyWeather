package com.example.myweather

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {
    @GET("/data/2.5/weather")
    fun getWeather(@Query("q") myCity: String, @Query("appid") myKey: String): Observable<MyWeather>

}
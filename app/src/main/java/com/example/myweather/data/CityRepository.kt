package com.example.myweather.data

import com.example.myweather.data.local.CityDao
import javax.inject.Inject

class CityRepository @Inject constructor(
        private var cityDao: CityDao
) {
    fun getCityLocalDataSource()= cityDao
}
package com.example.myweather.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class City(
    @PrimaryKey
    val city_name: String,
    @ColumnInfo(name = "city_lat") val lat: Double,
    @ColumnInfo(name = "city_lon") val lon: Double
)

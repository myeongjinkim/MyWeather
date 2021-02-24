package com.example.myweather

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CityDao {
    @Query("SELECT * FROM city")
    fun getAll(): List<City>

    @Query("SELECT * FROM city WHERE city_name = (:city_name)")
    fun load(city_name: String): City

    @Insert
    fun insert(vararg city: City)

    @Delete
    fun delete(city: City)
}
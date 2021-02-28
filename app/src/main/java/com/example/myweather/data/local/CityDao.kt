package com.example.myweather.data.local

import androidx.room.*

@Dao
interface CityDao {
    @Query("SELECT * FROM city")
    suspend fun getAll(): List<City>

    @Query("SELECT * FROM city WHERE city_name = (:city_name)")
    suspend fun load(city_name: String): City

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg city: City)

    @Query("DELETE FROM city")
    suspend fun deleteAll()
}
package com.example.myweather

import androidx.room.*

@Dao
interface CityDao {
    @Query("SELECT * FROM city")
    fun getAll(): List<City>

    @Query("SELECT * FROM city WHERE city_name = (:city_name)")
    fun load(city_name: String): City

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg city: City)

    @Query("DELETE FROM city")
    suspend fun deleteAll()
}
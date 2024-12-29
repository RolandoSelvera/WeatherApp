package com.rolandoselvera.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rolandoselvera.data.local.models.WeatherEntity

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: WeatherEntity)

    @Query("SELECT * FROM weather")
    suspend fun getAllWeather(): List<WeatherEntity>

    @Delete
    suspend fun delete(weather: WeatherEntity)

    @Update
    suspend fun updateWeather(weather: WeatherEntity)

    @Query("SELECT * FROM weather WHERE locationName = :locationName AND locationRegion = :locationRegion LIMIT 1")
    suspend fun getWeather(locationName: String, locationRegion: String): WeatherEntity?

    @Query("SELECT * FROM weather WHERE locationName = :name AND locationRegion = :region LIMIT 1")
    suspend fun getWeatherByLocation(name: String, region: String): WeatherEntity?

    @Query("DELETE FROM weather")
    suspend fun deleteAllWeather()
}

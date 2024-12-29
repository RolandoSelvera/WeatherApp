package com.rolandoselvera.data.local.repositories

import com.rolandoselvera.data.local.database.WeatherDao
import com.rolandoselvera.data.local.models.WeatherEntity
import javax.inject.Inject

class WeatherDbRepository @Inject constructor(
    private val weatherDao: WeatherDao
) {
    suspend fun saveWeather(weather: WeatherEntity) = weatherDao.insertWeather(weather)

    suspend fun saveWeatherIfNew(weatherEntity: WeatherEntity) {
        val existingWeather =
            weatherDao.getWeather(weatherEntity.locationName, weatherEntity.locationRegion)
        if (existingWeather == null) {
            weatherDao.insertWeather(weatherEntity)
        }
    }

    suspend fun getAllWeather(): List<WeatherEntity> {
        return weatherDao.getAllWeather()
    }

    suspend fun getWeatherByLocation(name: String, region: String): WeatherEntity? {
        return weatherDao.getWeatherByLocation(name, region)
    }

    suspend fun deleteWeather(weather: WeatherEntity) {
        weatherDao.delete(weather)
    }

    suspend fun clearWeather() = weatherDao.deleteAllWeather()
}
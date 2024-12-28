package com.rolandoselvera.data.remote.repositories

import com.rolandoselvera.BuildConfig
import com.rolandoselvera.data.remote.ApiService
import com.rolandoselvera.data.remote.models.responses.WeatherResponse
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiService: ApiService) {
    private val apiKey = BuildConfig.API_KEY

    suspend fun fetchWeather(location: String): WeatherResponse {
        return apiService.getWeather(apiKey, location, "no")
    }
}
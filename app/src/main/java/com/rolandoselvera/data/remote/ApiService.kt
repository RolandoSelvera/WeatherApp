package com.rolandoselvera.data.remote

import com.rolandoselvera.data.remote.models.responses.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("current.json")
    suspend fun getWeather(
        @Query("key") apiKey: String,
        @Query("q") query: String,
        @Query("aqi") aqi: String
    ): WeatherResponse
}
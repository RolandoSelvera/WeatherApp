package com.rolandoselvera.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rolandoselvera.data.remote.models.responses.Condition
import com.rolandoselvera.data.remote.models.responses.Current
import com.rolandoselvera.data.remote.models.responses.Location
import com.rolandoselvera.data.remote.models.responses.WeatherResponse

@Entity(tableName = "weather")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val condition: String,
    val tempC: Double,
    val locationName: String,
    val locationRegion: String,
    val country: String,
    val urlIcon: String,
    val localTime: String,
    val windKph: Double,
    val winDir: String,
    val humidity: Int,
)

fun WeatherResponse.toWeatherEntity(): WeatherEntity {
    return WeatherEntity(
        condition = current.condition.text,
        tempC = current.tempC,
        locationName = location.name,
        locationRegion = location.region,
        country = location.country,
        urlIcon = "https:${current.condition.icon}",
        localTime = location.localtime,
        windKph = current.windKph,
        winDir = current.windDir,
        humidity = current.humidity
    )
}

fun WeatherEntity.toWeatherResponse(): WeatherResponse {
    return WeatherResponse(
        location = Location(
            name = locationName,
            region = locationRegion,
            country = country,
            lat = 0.0,
            lon = 0.0,
            tzId = "",
            localtimeEpoch = 0,
            localtime = localTime
        ),
        current = Current(
            lastUpdatedEpoch = 0,
            lastUpdated = "",
            tempC = tempC,
            tempF = 0.0,
            isDay = 0,
            condition = Condition(
                text = condition,
                icon = urlIcon,
                code = 0
            ),
            windMph = 0.0,
            windKph = windKph,
            windDegree = 0,
            windDir = winDir,
            pressureMb = 0.0,
            pressureIn = 0.0,
            precipMm = 0.0,
            precipIn = 0.0,
            humidity = humidity,
            cloud = 0,
            feelslikeC = 0.0,
            feelslikeF = 0.0,
            windchillC = 0.0,
            windchillF = 0.0,
            heatindexC = 0.0,
            heatindexF = 0.0,
            dewpointC = 0.0,
            dewpointF = 0.0,
            visKm = 0.0,
            visMiles = 0.0,
            uv = 0.0,
            gustMph = 0.0,
            gustKph = 0.0
        )
    )
}
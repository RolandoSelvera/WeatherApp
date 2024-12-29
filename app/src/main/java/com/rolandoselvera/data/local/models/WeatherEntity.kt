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
    val urlIcon: String
)

fun WeatherResponse.toWeatherEntity(): WeatherEntity {
    return WeatherEntity(
        condition = current.condition.text,
        tempC = current.tempC,
        locationName = location.name,
        locationRegion = location.region,
        urlIcon = "https:${current.condition.icon}"
    )
}

fun WeatherEntity.toWeatherResponse(): WeatherResponse {
    return WeatherResponse(
        location = Location(
            name = locationName,
            region = locationRegion,
            country = "",
            lat = 0.0,
            lon = 0.0,
            tzId = "",
            localtimeEpoch = 0,
            localtime = ""
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
            windKph = 0.0,
            windDegree = 0,
            windDir = "",
            pressureMb = 0.0,
            pressureIn = 0.0,
            precipMm = 0.0,
            precipIn = 0.0,
            humidity = 0,
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
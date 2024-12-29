package com.rolandoselvera.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rolandoselvera.data.local.models.WeatherEntity

@Database(entities = [WeatherEntity::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}

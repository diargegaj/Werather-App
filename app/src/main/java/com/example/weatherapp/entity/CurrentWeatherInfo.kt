package com.example.weatherapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_info")
data class CurrentWeatherInfo(
        @ColumnInfo(name = "city_primary_id") val cityPrimaryId: Int,
        val description: String,
        val icon: String,
        @ColumnInfo(name = "current_temp") val currentTemp: Double,
        @ColumnInfo(name = "wind_speed") val windSpeed: Double,
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
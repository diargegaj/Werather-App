package com.example.weatherapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forecast_weather_Info")
data class ForecastWeatherInfo(
        @ColumnInfo(name = "city_primary_id") val cityPrimaryId: Int,
        @ColumnInfo(name = "min_temp") val minTemp: Double,
        @ColumnInfo(name = "max_temp") val maxTemp: Double,
        val date: String,
        val hour: String
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}

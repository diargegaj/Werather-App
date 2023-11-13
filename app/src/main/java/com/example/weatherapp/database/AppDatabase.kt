package com.example.weatherapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp.dao.CityDao
import com.example.weatherapp.entity.City
import com.example.weatherapp.entity.CurrentWeatherInfo
import com.example.weatherapp.entity.ForecastWeatherInfo
import com.example.weatherapp.model.CityModel

@Database(entities = [City::class, CurrentWeatherInfo::class, ForecastWeatherInfo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}
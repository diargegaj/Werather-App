package com.example.weatherapp.entity.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.example.weatherapp.entity.City
import com.example.weatherapp.entity.CurrentWeatherInfo

data class CityAndCurrentWeatherInfo(
        @Embedded val city: City,
        @Relation(
                parentColumn = "city_id",
                entityColumn = "city_primary_id"
        )
        var temperatureInfoCurrent: CurrentWeatherInfo?
)
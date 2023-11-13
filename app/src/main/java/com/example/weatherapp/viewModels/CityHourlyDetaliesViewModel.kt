package com.example.weatherapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.entity.relationships.CityAndForecastWeatherInfo
import com.example.weatherapp.repositories.WeatherRepository

class CityHourlyDetaliesViewModel(cityId : Int) : ViewModel() {
    private val repo = WeatherRepository(cityId)

    val forecastWeatherInfo: LiveData<CityAndForecastWeatherInfo?>
        get() = repo.forecastWeatherInfo()
}
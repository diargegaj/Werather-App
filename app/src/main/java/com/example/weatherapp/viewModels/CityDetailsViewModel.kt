package com.example.weatherapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.repositories.WeatherRepository
import com.example.weatherapp.entity.relationships.CityAndCurrentWeatherInfo
import com.example.weatherapp.entity.relationships.CityAndForecastWeatherInfo

class CityDetailsViewModel(val cityId : Int) : ViewModel() {
    private val repo = WeatherRepository(cityId)

    val todayWeatherInfo: LiveData<CityAndCurrentWeatherInfo?>
        get() = repo.currentWeatherInfo()

    val forecastWeatherInfo: LiveData<CityAndForecastWeatherInfo?>
        get() = repo.forecastWeatherInfo()

}
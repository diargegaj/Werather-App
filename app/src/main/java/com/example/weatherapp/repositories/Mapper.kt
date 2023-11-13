package com.example.weatherapp.repositories

import com.example.weatherapp.entity.CurrentWeatherInfo
import com.example.weatherapp.entity.ForecastWeatherInfo
import com.example.weatherapp.model.CityModel
import com.example.weatherapp.model.WeatherInfoModel

class Mapper {
    fun convertCityModelToCurrentWeatherInfo(cityId: Int, cityModel: CityModel): CurrentWeatherInfo{
        return CurrentWeatherInfo(cityId, cityModel.weather!![0].description, cityModel.weather!![0].icon ,cityModel.main!!.temp, cityModel.wind!!.speed)
    }

    fun convertListOfWeatherInfoModelToForecastWeatherInfo(cityId: Int, weatherInfoModels: List<WeatherInfoModel>): List<ForecastWeatherInfo> {
        val forecastWeatherInfo = ArrayList<ForecastWeatherInfo>()

        for (element in weatherInfoModels){
            forecastWeatherInfo.add(ForecastWeatherInfo(cityId, element.main.tempMin, element.main.tempMax, element.onlyDate, element.onlyHour))
        }
        return forecastWeatherInfo
    }
}
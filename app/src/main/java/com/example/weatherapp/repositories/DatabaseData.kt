package com.example.weatherapp.repositories

import androidx.lifecycle.LiveData
import com.example.weatherapp.RoomInstance
import com.example.weatherapp.entity.CurrentWeatherInfo
import com.example.weatherapp.entity.relationships.CityAndCurrentWeatherInfo
import com.example.weatherapp.entity.ForecastWeatherInfo
import com.example.weatherapp.entity.relationships.CityAndForecastWeatherInfo

class DatabaseData {
    private val cityDao = RoomInstance.db.cityDao()

    fun getTodayWeatherInfo(cityId: Int): LiveData<CityAndCurrentWeatherInfo?> {
        return cityDao.getCityAndCurrentWeatherInfo(cityId)
    }

    fun updateTodayWeatherInfo(currentWeatherInfo: CurrentWeatherInfo): Long{
        cityDao.deleteAllWeatherInfoForCityID(currentWeatherInfo.cityPrimaryId)
        return cityDao.insertCurrentWeatherInfo(currentWeatherInfo)
    }

    fun getForecastWeatherInfo(cityId: Int): LiveData<CityAndForecastWeatherInfo?> {
        return cityDao.getForecastWeatherData(cityId)
    }

    fun updateForecastWeatherInfo(forecastWeatherInfo: List<ForecastWeatherInfo>): List<Long>{
        cityDao.deleteAllForecastWeatherInfoForCityID(forecastWeatherInfo[0].cityPrimaryId)
        return cityDao.insertForecastWeatherInfo(forecastWeatherInfo)
    }
}
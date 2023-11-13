package com.example.weatherapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.weatherapp.entity.City
import com.example.weatherapp.entity.CurrentWeatherInfo
import com.example.weatherapp.entity.ForecastWeatherInfo
import com.example.weatherapp.entity.relationships.CityAndCurrentWeatherInfo
import com.example.weatherapp.entity.relationships.CityAndForecastWeatherInfo
import com.example.weatherapp.model.CityModel

@Dao
interface CityDao {
    @Transaction
    @Query("SELECT * FROM city INNER JOIN weather_info ON weather_info.city_primary_id = city.city_id WHERE city.city_id = :cityId")
    fun getCityAndCurrentWeatherInfo(cityId: Int): LiveData<CityAndCurrentWeatherInfo?>

    @Query("SELECT * FROM weather_info WHERE city_primary_id = :cityId")
    fun getCurrentWeatherInfo(cityId: Int): LiveData<CurrentWeatherInfo?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrentWeatherInfo(currentWeatherInfo: CurrentWeatherInfo): Long

    @Transaction
    @Query("SELECT * FROM forecast_weather_Info INNER JOIN city ON forecast_weather_Info.city_primary_id = city.city_id WHERE city.city_id = :cityId")
    fun getForecastWeatherData(cityId: Int): LiveData<CityAndForecastWeatherInfo?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertForecastWeatherInfo(forecastWeatherInfo: List<ForecastWeatherInfo>): List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCities(cities: List<City>)

    @Query("DELETE FROM weather_info WHERE city_primary_id = :cityPrimaryId")
    fun deleteAllWeatherInfoForCityID(cityPrimaryId: Int)

    @Query("DELETE FROM forecast_weather_Info WHERE city_primary_id = :cityPrimaryId")
    fun deleteAllForecastWeatherInfoForCityID(cityPrimaryId: Int)
}
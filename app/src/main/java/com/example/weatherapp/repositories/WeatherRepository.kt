
package com.example.weatherapp.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.weatherapp.CityDetailsActivity
import com.example.weatherapp.RetrofitInstance
import com.example.weatherapp.entity.relationships.CityAndCurrentWeatherInfo
import com.example.weatherapp.entity.relationships.CityAndForecastWeatherInfo
import com.example.weatherapp.service.OpenWeatherService
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import com.example.weatherapp.TAG

class WeatherRepository(val cityId: Int) {

    private val database: DatabaseData = DatabaseData()
    private val mapper: Mapper = Mapper()

    private val service = RetrofitInstance.opemWeatherService

    fun currentWeatherInfo(): LiveData<CityAndCurrentWeatherInfo?> {
        val dis: Disposable = service.getWeather(cityId, OpenWeatherService.API_KEY)
                .subscribeOn(Schedulers.io())
                .map { cityModel -> mapper.convertCityModelToCurrentWeatherInfo(cityId, cityModel) }
                .subscribe(
                        { currentWeatherInfo->
                            if (currentWeatherInfo != null){
                                Log.d(TAG, "insertef to DB ${currentWeatherInfo.cityPrimaryId}")
                                database.updateTodayWeatherInfo(currentWeatherInfo)
                            }
                        },
                        {it.printStackTrace()}
                )
        Log.d(TAG, "returnet from db")
        return database.getTodayWeatherInfo(cityId)
    }

    fun forecastWeatherInfo(): LiveData<CityAndForecastWeatherInfo?> {
        val dis: Disposable = service.getForecast(cityId,CityDetailsActivity.WEATHER_FORECAST_DAYS, OpenWeatherService.API_KEY)
                .subscribeOn(Schedulers.io())
                .map { weatherForecastModel ->  mapper.convertListOfWeatherInfoModelToForecastWeatherInfo(cityId, weatherForecastModel.weatherInfoModels) }
                .subscribe(
                        { forecastWeatherInfos ->
                            if (forecastWeatherInfos != null){
                                database.updateForecastWeatherInfo(forecastWeatherInfos)
                            }
                        },
                        {it.printStackTrace()}
                )

        return database.getForecastWeatherInfo(cityId)
    }
}
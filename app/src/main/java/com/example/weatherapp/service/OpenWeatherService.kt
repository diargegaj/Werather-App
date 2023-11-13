package com.example.weatherapp.service

import com.example.weatherapp.model.CityModel
import com.example.weatherapp.model.WeatherForecastModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {
    @GET("data/2.5/weather?units=metric&lang=al")
    fun getWeather(@Query("id") cityId: Int, @Query("appid") apiKey: String?): Observable<CityModel>

    @GET("data/2.5/forecast?units=metric&lang=al")
    fun getForecast(@Query("id") cityId: Int, @Query("qnt") days: Int, @Query("appid") apiKey: String?): Observable<WeatherForecastModel>

    companion object {
        const val API_KEY = "2f63ee81a0672c811f257eebee41490a"
        const val URL = "https://api.openweathermap.org/"
    }
}
package com.example.weatherapp

import com.example.weatherapp.service.OpenWeatherService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val retroift: Retrofit by lazy {
        Retrofit.Builder()
                    .baseUrl(OpenWeatherService.URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
    }

    val opemWeatherService: OpenWeatherService by lazy {
        retroift.create(OpenWeatherService::class.java)
    }
}
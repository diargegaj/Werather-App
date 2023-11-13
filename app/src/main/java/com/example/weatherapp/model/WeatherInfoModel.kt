package com.example.weatherapp.model

import com.google.gson.annotations.SerializedName

class WeatherInfoModel(var main: TemperatureInfoModel, var weather: List<WeatherModel>, @field:SerializedName("dt_txt") var date: String) {

    val onlyDate: String
        get() = date.split(" ").toTypedArray()[0]
    val onlyHour: String
        get() = date.split(" ").toTypedArray()[1]
}
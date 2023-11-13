package com.example.weatherapp.model

import com.google.gson.annotations.SerializedName

class TemperatureInfoModel(var temp: Double, @field:SerializedName("temp_min") var tempMin: Double, @field:SerializedName("temp_max") var tempMax: Double){

}
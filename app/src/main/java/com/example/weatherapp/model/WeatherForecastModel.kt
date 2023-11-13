package com.example.weatherapp.model

import com.google.gson.annotations.SerializedName
import java.util.*

class WeatherForecastModel(@field:SerializedName("list") var weatherInfoModels: List<WeatherInfoModel>) {
    val onlyByDate: List<WeatherDateModel>
        get() {
            val tempModelsOnlyByDate: MutableList<WeatherDateModel> = ArrayList()
            var minTemp = weatherInfoModels[0].main.tempMin
            var maxTemp = 0.0
            for (i in 0 until weatherInfoModels.size - 1) {
                val currentModel = weatherInfoModels[i]
                val nextModel = weatherInfoModels[i + 1]
                val currentMaxTemp = currentModel.main.tempMax
                if (maxTemp < currentMaxTemp) {
                    maxTemp = currentMaxTemp
                }
                val currentMinTemp = currentModel.main.tempMin
                if (minTemp > currentMinTemp) {
                    minTemp = currentMinTemp
                }
                if (currentModel.onlyDate != nextModel.onlyDate || i == weatherInfoModels.size - 2) {
                    tempModelsOnlyByDate.add(WeatherDateModel(minTemp, maxTemp, currentModel.onlyDate))
                    minTemp = weatherInfoModels[i + 1].main.tempMin
                    maxTemp = 0.0
                }
            }
            return tempModelsOnlyByDate
        }

    fun getOnlyByHour(date: String?): List<WeatherDateModel> {
        val tempModelsOnlyByDate: MutableList<WeatherDateModel> = ArrayList()
        for (i in weatherInfoModels.indices) {
            val currentModel = weatherInfoModels[i]
            if (currentModel.date.split(" ").toTypedArray()[0] == date) {
                val minTemp = currentModel.main.tempMin
                val maxTemp = currentModel.main.tempMax
                tempModelsOnlyByDate.add(WeatherDateModel(minTemp, maxTemp, currentModel.onlyHour))
            }
        }
        return tempModelsOnlyByDate
    }
}
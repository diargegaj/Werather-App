package com.example.weatherapp.entity.relationships

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import com.example.weatherapp.entity.City
import com.example.weatherapp.entity.ForecastWeatherInfo
import com.example.weatherapp.model.WeatherDateModel
import java.util.ArrayList

data class CityAndForecastWeatherInfo(
        @Embedded val city: City,
        @Relation(
                parentColumn = "city_id",
                entityColumn = "city_primary_id"
        )
        val forecastInfo: List<ForecastWeatherInfo>
){
        val onlyByDate: List<WeatherDateModel>
                get() {
                        val tempModelsOnlyByDate: MutableList<WeatherDateModel> = ArrayList()
                        var minTemp = forecastInfo[0].minTemp
                        var maxTemp = 0.0
                        for (i in 0 until forecastInfo.size - 1) {
                                val currentModel = forecastInfo[i]
                                val nextModel = forecastInfo[i + 1]
                                val currentMaxTemp = currentModel.maxTemp
                                if (maxTemp < currentMaxTemp) {
                                        maxTemp = currentMaxTemp
                                }
                                val currentMinTemp = currentModel.minTemp
                                if (minTemp > currentMinTemp) {
                                        minTemp = currentMinTemp
                                }
                                if (currentModel.date != nextModel.date || i == forecastInfo.size - 2) {
                                        tempModelsOnlyByDate.add(WeatherDateModel(minTemp, maxTemp, currentModel.date))
                                        minTemp = forecastInfo[i + 1].minTemp
                                        maxTemp = 0.0
                                }
                        }
                        return tempModelsOnlyByDate
                }

        fun getOnlyByHour(date: String?): List<WeatherDateModel> {
                val tempModelsOnlyByDate: MutableList<WeatherDateModel> = ArrayList()
                for (i in forecastInfo.indices) {
                        val currentModel = forecastInfo[i]
                        if (currentModel.date == date) {
                                val minTemp = currentModel.minTemp
                                val maxTemp = currentModel.maxTemp
                                tempModelsOnlyByDate.add(WeatherDateModel(minTemp, maxTemp, currentModel.hour))
                        }
                }
                return tempModelsOnlyByDate
        }

}

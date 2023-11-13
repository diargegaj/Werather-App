package com.example.weatherapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ModifyViewModelFactory(private val cityId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CityDetailsViewModel::class.java)){
            return CityDetailsViewModel(cityId) as T
        }else if (modelClass.isAssignableFrom(CityHourlyDetaliesViewModel::class.java)){
            return CityHourlyDetaliesViewModel(cityId) as T
        }
        throw IllegalArgumentException("ViewModel class not found")
    }
}
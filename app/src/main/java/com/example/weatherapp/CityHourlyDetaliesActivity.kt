package com.example.weatherapp

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.adapter.WeatherHourlyAdapter
import com.example.weatherapp.entity.relationships.CityAndForecastWeatherInfo
import com.example.weatherapp.viewModels.CityHourlyDetaliesViewModel
import com.example.weatherapp.viewModels.ModifyViewModelFactory

class CityHourlyDetaliesActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day_detalies)

        val loading : ProgressBar = findViewById(R.id.loading)
        val hourlyRecycleView = findViewById<RecyclerView>(R.id.weatherHourlyInfo)
        hourlyRecycleView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        val cityId = intent.getIntExtra(CityDetailsActivity.CITY_ID_ARG, -1)
        val date = intent.getStringExtra(CityDetailsActivity.DATE_TO_SEARCH)

        val viewModelProviderFactory = ModifyViewModelFactory(cityId)
        val cityHourlyDetaliesViewModel = ViewModelProvider(this, viewModelProviderFactory).get(CityHourlyDetaliesViewModel::class.java)

        val weatherForecastAdapter = WeatherHourlyAdapter()

        cityHourlyDetaliesViewModel.forecastWeatherInfo.observe(this, { viewData: CityAndForecastWeatherInfo? ->
            if (viewData != null) {
                val tempModels = viewData.getOnlyByHour(date)
                weatherForecastAdapter.setModels(tempModels)
                hourlyRecycleView.adapter = weatherForecastAdapter
                loading.visibility = View.INVISIBLE
            }
        })

        hourlyRecycleView.layoutManager = LinearLayoutManager(this@CityHourlyDetaliesActivity)
    }
}
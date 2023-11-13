package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.example.weatherapp.adapter.WeatherForecastAdapter
import com.example.weatherapp.entity.relationships.CityAndCurrentWeatherInfo
import com.example.weatherapp.entity.relationships.CityAndForecastWeatherInfo
import com.example.weatherapp.model.WeatherDateModel
import com.example.weatherapp.viewModels.CityDetailsViewModel
import com.example.weatherapp.viewModels.ModifyViewModelFactory
import kotlin.text.*

const val TAG_DEBUG = "DEBUG"
class CityDetailsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_details)

        val currentTemp : TextView = findViewById(R.id.currentTemp)
        val windSpeed : TextView = findViewById(R.id.windSpeed)
        val countryAndCityName : TextView = findViewById(R.id.countryAndCityName)
        val description : TextView = findViewById(R.id.weatherDescriptionText)
        val weatherIcon : ImageView = findViewById(R.id.weatherIcon)
        val process : ProgressBar = findViewById(R.id.process)
        val cityId : Int = intent.getIntExtra(CITY_ID_ARG, -1)
        val countryName = intent.getStringExtra(COUNTRY_NAME)
        val refresh: SwipeRefreshLayout = findViewById(R.id.refresh)

        val viewModelProviderFactory = ModifyViewModelFactory(cityId)
        val cityDetailsViewModel: CityDetailsViewModel = ViewModelProvider(this, viewModelProviderFactory).get(CityDetailsViewModel::class.java)

        cityDetailsViewModel.todayWeatherInfo.observe(this, { viewData: CityAndCurrentWeatherInfo? ->
            if(viewData != null){
                description.text = viewData.temperatureInfoCurrent!!.description
                currentTemp.text = String.format("%s℃", viewData.temperatureInfoCurrent!!.currentTemp)
                windSpeed.text = String.format("%skm/h", viewData.temperatureInfoCurrent!!.windSpeed)
                countryAndCityName.text = String.format("%s,\n%s", countryName, viewData.city.name)

                val iconName = viewData.temperatureInfoCurrent!!.icon
                val iconUrl = String.format("https://openweathermap.org/img/w/%s.png", iconName)
                Glide.with(this@CityDetailsActivity).load(iconUrl).into(weatherIcon)
            }
        })

        val forecastDataRecyleView = findViewById<RecyclerView>(R.id.weatherForecastRecyleView)
        forecastDataRecyleView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        val weatherForecastAdapter = WeatherForecastAdapter()

        cityDetailsViewModel.forecastWeatherInfo.observe(this, { viewData: CityAndForecastWeatherInfo? ->
            if (viewData != null) {
                val tempModels: List<WeatherDateModel> = viewData.onlyByDate
                weatherForecastAdapter.setModels(tempModels)
                forecastDataRecyleView.adapter = weatherForecastAdapter
                process.visibility = View.INVISIBLE
            }
        })

        refresh.setOnRefreshListener{
            Log.d(TAG_DEBUG, "DEBUG")
            refresh.isRefreshing = false
        }

        weatherForecastAdapter.setOnClickListener(object : WeatherForecastAdapter.ItemInteraction{
            override fun openHourlyInfo(date: String?) {
                val intent = Intent(this@CityDetailsActivity, CityHourlyDetaliesActivity::class.java)
                intent.putExtra(CITY_ID_ARG, cityId)
                intent.putExtra(DATE_TO_SEARCH, date)
                startActivity(intent)
            }

        })

        forecastDataRecyleView.layoutManager = LinearLayoutManager(this)
    }

    companion object {
        const val CITY_ID_ARG = "cityId"
        const val WEATHER_FORECAST_DAYS = 16
        const val DATE_TO_SEARCH = "date"
        const val COUNTRY_NAME = "countryName"
    }
}
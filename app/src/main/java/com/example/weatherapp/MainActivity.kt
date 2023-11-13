package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.adapter.WeatherLocationAdapter
import com.example.weatherapp.model.CityModel
import com.example.weatherapp.model.CountryModel
import com.example.weatherapp.model.WeatherLocationModel
import kotlin.collections.ArrayList

const val TAG = "WeatherApp-TAG"
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val countriesRecyleView = findViewById<RecyclerView>(R.id.countriesRecyleView)
        val countries = initializeCountries()
        val adapter = WeatherLocationAdapter(countries)

        adapter.setOnClickListener(object : WeatherLocationAdapter.CityInteraction{
            override fun openDetailInfo(cityId: Int, countryName: String?) {
                val intent = Intent(this@MainActivity, CityDetailsActivity::class.java)
                intent.putExtra(CityDetailsActivity.CITY_ID_ARG, cityId)
                intent.putExtra(CityDetailsActivity.COUNTRY_NAME, countryName)
                startActivity(intent)
            }
        })

        countriesRecyleView.adapter = adapter
        countriesRecyleView.layoutManager = LinearLayoutManager(this)
    }

    private fun initializeCountries(): ArrayList<WeatherLocationModel> {
        val countries = ArrayList<WeatherLocationModel>()
        val kosovo = CountryModel("Kosovo")
        countries.add(kosovo)
        countries.add(CityModel(786714, "Prishtina", kosovo))
        countries.add(CityModel(786950, "Podujeva", kosovo))
        countries.add(CityModel(786712, "Prizren", kosovo))
        countries.add(CityModel( 785238, "Suharek", kosovo))
        countries.add(CityModel( 787157, "Pej", kosovo))
        countries.add(CityModel(789996, "Istog", kosovo))
        countries.add(CityModel(791580, "Deqan", kosovo))

        val albania = CountryModel("Albania")
        countries.add(albania)

        countries.add(CityModel(3183875, "Tirana", albania))
        countries.add(CityModel(3185728, "Durres", albania))
        countries.add(CityModel(3183719, "Vlore", albania))
        countries.add(CityModel(3186084, "Berat", albania))
        countries.add(CityModel(782661, "Kukes", albania))
        countries.add(CityModel(3184081, "Shkoder", albania))
        countries.add(CityModel(363243, "Sarande", albania))
        countries.add(CityModel(363383, "Delvine", albania))

        val germany = CountryModel("Germany")
        countries.add(germany)

        countries.add(CityModel(2950159, "Berlin", germany))
        countries.add(CityModel(2867714, "Munich", germany))
        countries.add(CityModel(2925533, "Frankfurt", germany))
        countries.add(CityModel(2935517, "Dortmund", germany))
        countries.add(CityModel(2886242, "Colonge", germany))
        countries.add(CityModel(2946447, "Bonn", germany))

        val italy = CountryModel("Italy")
        countries.add(italy)

        countries.add(CityModel(3169070, "Roma", italy))
        countries.add(CityModel(3173435, "Milan", italy))
        countries.add(CityModel(2523918, "Palermo", italy))

        return countries
    }


}

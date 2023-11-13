package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.adapter.WeatherLocationAdapter.WeatherLocationViewHolder
import com.example.weatherapp.model.CityModel
import com.example.weatherapp.model.CountryModel
import com.example.weatherapp.model.WeatherLocationModel

class WeatherLocationAdapter(var models: List<WeatherLocationModel>) : RecyclerView.Adapter<WeatherLocationViewHolder>() {
    var cityInteraction: CityInteraction? = null
    fun setOnClickListener(cityIreactor: CityInteraction?) {
        cityInteraction = cityIreactor
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherLocationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == VIEW_TYPE_CITY) {
            val view = inflater.inflate(R.layout.city_item, parent, false)
            CityViewHolder(view, cityInteraction)
        } else {
            val view = inflater.inflate(R.layout.country_item, parent, false)
            CountryViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: WeatherLocationViewHolder, position: Int) {
        if (models[position] is CityModel) {
            val cityModel = models[position] as CityModel
            holder.fillView(cityModel.name)
        } else {
            val countryModel = models[position] as CountryModel
            holder.fillView(countryModel.name)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (models[position] is CountryModel) VIEW_TYPE_COUNTRY else VIEW_TYPE_CITY
    }

    override fun getItemCount(): Int {
        return models.size
    }

    interface CityInteraction {
        fun openDetailInfo(cityId: Int, countryName: String?)
    }

    abstract class WeatherLocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun fillView(name: String?)
    }

    inner class CityViewHolder(itemView: View, cityIreactor: CityInteraction?) : WeatherLocationViewHolder(itemView) {
        private val cityName: TextView
        private val cityInteraction: CityInteraction?
        override fun fillView(cityName: String?) {
            this.cityName.text = cityName
            itemView.setOnClickListener { v: View? ->
                val adapterPosition = adapterPosition
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val cityModel = models[adapterPosition] as CityModel
                    val cityId = cityModel.cityId
                    val countryName = cityModel.country!!.name
                    cityInteraction?.openDetailInfo(cityId, countryName)
                }
            }
        }

        init {
            cityName = itemView.findViewById(R.id.cityName)
            this.cityInteraction = cityIreactor
        }
    }

    class CountryViewHolder(itemView: View) : WeatherLocationViewHolder(itemView) {
        var countryName: TextView
        override fun fillView(countryName: String?) {
            this.countryName.text = countryName
        }

        init {
            countryName = itemView.findViewById(R.id.countryName)
        }
    }

    companion object {
        const val VIEW_TYPE_CITY = 1
        const val VIEW_TYPE_COUNTRY = 2
    }
}
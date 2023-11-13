package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.adapter.WeatherHourlyAdapter.WeatherHourlyViewHolder
import com.example.weatherapp.model.WeatherDateModel

class WeatherHourlyAdapter : RecyclerView.Adapter<WeatherHourlyViewHolder>() {
    var models: List<WeatherDateModel>? = null

    @JvmName("setModels1")
    fun setModels(models: List<WeatherDateModel>?) {
        this.models = models
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHourlyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather_day, parent, false)
        return WeatherHourlyViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherHourlyViewHolder, position: Int) {
        val tempModel = models!![position]
        holder.fillView(tempModel.date, tempModel.minTemp, tempModel.maxTemp)
    }

    override fun getItemCount(): Int {
        return models!!.size
    }

    class WeatherHourlyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var date: TextView
        var minTemp: TextView
        var maxTemp: TextView

        fun fillView(date: String?, minTemp: Double, maxTemp: Double) {
            this.date.text = date
            this.minTemp.text = String.format("%s℃", minTemp)
            this.maxTemp.text = String.format("%s℃", maxTemp)
        }

        init {
            date = itemView.findViewById(R.id.date)
            minTemp = itemView.findViewById(R.id.minTempInDayItem)
            maxTemp = itemView.findViewById(R.id.maxTempInDayItem)
        }
    }
}
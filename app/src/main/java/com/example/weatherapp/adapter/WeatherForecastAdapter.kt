package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.adapter.WeatherForecastAdapter.ForecastViewHolder
import com.example.weatherapp.model.WeatherDateModel

class WeatherForecastAdapter : RecyclerView.Adapter<ForecastViewHolder>() {
    var models: List<WeatherDateModel>? = null
    var itemInteraction: ItemInteraction? = null

    @JvmName("setModels1")
    fun setModels(models: List<WeatherDateModel>?) {
        this.models = models
        notifyDataSetChanged()
    }

    fun setOnClickListener(itemInteraction: ItemInteraction?) {
        this.itemInteraction = itemInteraction
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather_day, parent, false)
        return ForecastViewHolder(view, itemInteraction)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val tempModel = models!![position]
        holder.fillView(tempModel.date, tempModel.minTemp, tempModel.maxTemp)
    }

    override fun getItemCount(): Int {
        return models!!.size
    }

    interface ItemInteraction {
        fun openHourlyInfo(date: String?)
    }

    inner class ForecastViewHolder(itemView: View, itemInteraction: ItemInteraction?) : RecyclerView.ViewHolder(itemView) {
        var date: TextView
        var minTemp: TextView
        var maxTemp: TextView
        var itemInteraction: ItemInteraction?
        fun fillView(date: String?, minTemp: Double, maxTemp: Double) {
            this.date.text = date
            this.minTemp.text = String.format("%s℃", minTemp)
            this.maxTemp.text = String.format("%s℃", maxTemp)
            itemView.setOnClickListener { v: View? ->
                if (itemInteraction != null) {
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        itemInteraction!!.openHourlyInfo(models!![adapterPosition].date!!.split(" ").toTypedArray()[0])
                    }
                }
            }
        }

        init {
            date = itemView.findViewById(R.id.date)
            minTemp = itemView.findViewById(R.id.minTempInDayItem)
            maxTemp = itemView.findViewById(R.id.maxTempInDayItem)
            this.itemInteraction = itemInteraction
        }
    }
}
package com.example.weatherapp

import androidx.room.Room
import com.example.weatherapp.database.AppDatabase

object RoomInstance {
    val db:AppDatabase by lazy {
        Room.databaseBuilder(MyAPP.instance!!, AppDatabase::class.java, "weather-app-db")
                .build()
    }
}
package com.example.weatherapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city")
data class City(
        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = "city_id") val cityId: Int,
        val name: String,
        val country: String,
)
package com.example.weatherapp

import android.app.Application
import android.content.Context

class MyAPP : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        var instance: Context? = null
            private set
    }
}
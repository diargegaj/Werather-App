package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.weatherapp.entity.City
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class StarterActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starter)

        Observable.timer(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .map { configuration() }
                .subscribe{
                    val intent = Intent(this@StarterActivity, MainActivity::class.java)
                    startActivity(intent)
                }
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
        finish()
    }

    private fun configuration(){
        insetCitiesToDatabase()
        val configure = RetrofitInstance.opemWeatherService
    }

    private fun insetCitiesToDatabase(){
        val cities: ArrayList<City> = ArrayList()

        val prishtina = City( 786714,"Prishtina", "Kosovo")
        cities.add(prishtina)
        val podujeva= City(786950, "Podujeva", "Kosovo")
        cities.add(podujeva)
        val prizren = City(786712, "Prizren", "Kosovo")
        cities.add(prizren)
        val suharek = City(785238, "Suharek", "Kosovo")
        cities.add(suharek)
        val pej= City(787157, "Pej", "Kosovo")
        cities.add(pej)
        val istog= City(789996, "Istog", "Kosovo")
        cities.add(istog)
        val deqan = City(791580, "Deqan", "Kosovo")
        cities.add(deqan)

        val tirana = City(3183875, "Tirana", "Albania")
        cities.add(tirana)
        val durres = City(3185728, "Durres", "Albania")
        cities.add(durres)
        val vlore = City(3183719, "Vlore", "Albania")
        cities.add(vlore)
        val berat = City(3186084, "Berat", "Albania")
        cities.add(berat)
        val kukes = City(782661, "Kukes", "Albania")
        cities.add(kukes)
        val shkoder = City(3184081, "Shkoder", "Albania")
        cities.add(shkoder)
        val sarande = City(363243, "Sarande", "Albania")
        cities.add(sarande)
        val delvine = City(363383, "Delvine", "Albania")
        cities.add(delvine)

        val berlin = City(2950159, "Berlin", "Germany")
        cities.add(berlin)
        val munich = City(2867714, "Munich", "Germany")
        cities.add(munich)
        val frankfurt = City(2925533, "Frankfurt", "Germany")
        cities.add(frankfurt)
        val dortmund = City(2935517, "Dortmund", "Germany")
        cities.add(dortmund)
        val colonge = City(2886242, "Colonge", "Germany")
        cities.add(colonge)
        val bonn = City(2946447, "Bonn", "Germany")
        cities.add(bonn)

        val roma = City(3169070, "Roma", "Italy")
        cities.add(roma)
        val milan = City(3173435, "Milan", "Italy")
        cities.add(milan)
        val palermo = City(2523918, "Palermo", "Italy")
        cities.add(palermo)

        val dis: Disposable = Observable.just(cities)
                .subscribeOn(Schedulers.io())
                .subscribe{ cities ->
                    RoomInstance.db.cityDao().insertCities(cities)
                }
    }
}
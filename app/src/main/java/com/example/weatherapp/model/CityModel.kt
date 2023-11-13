package com.example.weatherapp.model

class CityModel : WeatherLocationModel {
    var cityId: Int
    override var name: String
        private set
    var main: TemperatureInfoModel? = null
        private set
    var wind: WindModel? = null
        private set
    var weather: List<WeatherModel>? = null
        private set
    var country: CountryModel? = null
        private set

    constructor(cityId: Int, name: String, main: TemperatureInfoModel?, wind: WindModel?, weather: List<WeatherModel>) {
        this.cityId = cityId
        this.name = name
        this.main = main
        this.wind = wind
        this.weather = weather
    }

    constructor(cityId: Int, name: String, country: CountryModel?) {
        this.cityId = cityId
        this.name = name
        this.country = country
    }

    override fun toString(): String {
        return "CityModel(cityId=$cityId, name='$name', main=$main, wind=$wind, weather=$weather, country=$country)"
    }

}
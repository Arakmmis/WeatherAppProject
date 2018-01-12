package me.arakmmis.weatherproject.businesslogic.pojos

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Forecast(
        @PrimaryKey
        var id: String,

        var cityName: String,

        var countryName: String,

        var dt: Int,

        var temp: Double,

        var weatherDesc: String,

        var windSpeed: Double
) : RealmObject() {

        constructor() : this("", "", "", 0, 0.0, "", 0.0)
}
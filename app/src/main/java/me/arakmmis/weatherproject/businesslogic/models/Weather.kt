package me.arakmmis.weatherproject.businesslogic.models

import io.realm.RealmObject
import me.arakmmis.weatherproject.R
import java.util.*

open class Weather(
        var desc: String,
        var secondaryDesc: String
) : RealmObject() {
    constructor() : this("", "")

    fun getDescPic(): Int {
        val isAM: Boolean = Calendar.getInstance().get(Calendar.AM_PM) == 0

        when (secondaryDesc.toLowerCase()) {
            "clear sky" -> return if (isAM) R.drawable.clear else R.drawable.clear_night
            "few clouds" -> return if (isAM) R.drawable.few_clouds else R.drawable.few_clouds_night
            "scattered clouds" -> return if (isAM) R.drawable.clouds else R.drawable.clouds_night
            "broken clouds" -> return if (isAM) R.drawable.clouds else R.drawable.clouds_night
            "shower rain" -> return if (isAM) R.drawable.showers_day else R.drawable.showers_night
            "rain" -> return if (isAM) R.drawable.rain_day else R.drawable.rain_night
            "light rain" -> return if (isAM) R.drawable.rain_day else R.drawable.rain_night
            "thunderstorm" -> return if (isAM) R.drawable.storm_day else R.drawable.storm_night
            "snow" -> return if (isAM) R.drawable.snow_scattered_day else R.drawable.snow_scattered_night
            "mist" -> return R.drawable.mist
            else -> return R.drawable.none_available
        }
    }
}
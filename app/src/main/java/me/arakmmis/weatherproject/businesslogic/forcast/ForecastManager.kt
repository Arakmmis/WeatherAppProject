package me.arakmmis.weatherproject.businesslogic.forcast

import io.reactivex.Single
import me.arakmmis.weatherproject.businesslogic.models.Forecast

interface ForecastManager {

    fun getForecastFor(city: String): Single<Forecast>
}
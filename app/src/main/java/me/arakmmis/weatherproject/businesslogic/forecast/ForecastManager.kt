package me.arakmmis.weatherproject.businesslogic.forecast

import io.reactivex.Single
import me.arakmmis.weatherproject.businesslogic.models.Forecast

interface ForecastManager {

    fun getForecastFor(city: String): Single<Forecast>
}
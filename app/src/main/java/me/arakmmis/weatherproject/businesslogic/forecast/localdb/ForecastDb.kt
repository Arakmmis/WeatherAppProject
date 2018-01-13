package me.arakmmis.weatherproject.businesslogic.forecast.localdb

import io.reactivex.Single
import me.arakmmis.weatherproject.businesslogic.forecast.ForecastManager
import me.arakmmis.weatherproject.businesslogic.models.Forecast

class ForecastDb : ForecastManager {

    override fun getForecastFor(city: String): Single<Forecast> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
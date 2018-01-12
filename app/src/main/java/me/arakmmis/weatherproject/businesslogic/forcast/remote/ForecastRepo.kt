package me.arakmmis.weatherproject.businesslogic.forcast.remote

import io.reactivex.Single
import me.arakmmis.weatherproject.businesslogic.forcast.ForecastManager
import me.arakmmis.weatherproject.businesslogic.pojos.Forecast

class ForecastRepo : ForecastManager {

    override fun getForecastFor(city: String): Single<Forecast> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
package me.arakmmis.weatherproject.businesslogic.forcast.remote

import io.reactivex.Single
import me.arakmmis.weatherproject.businesslogic.forcast.ForecastManager
import me.arakmmis.weatherproject.businesslogic.pojos.BufferForecast
import me.arakmmis.weatherproject.businesslogic.pojos.Forecast
import me.arakmmis.weatherproject.utils.ApiUtils

class ForecastRepo : ForecastManager {

    override fun getForecastFor(city: String): Single<Forecast> {
        return ApiUtils.retrofit
                .create(ForecastApi::class.java)
                .getForecast(city, ApiUtils.getApiKey())
                .map { bufferForecast: BufferForecast? -> bufferForecast?.toForecast() }
    }

}
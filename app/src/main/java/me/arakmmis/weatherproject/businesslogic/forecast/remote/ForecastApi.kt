package me.arakmmis.weatherproject.businesslogic.forecast.remote

import io.reactivex.Single
import me.arakmmis.weatherproject.businesslogic.models.BufferForecast
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApi {

    @GET("forecast?units=metric")
    fun getForecast(@Query("q") city: String,
                    @Query("appid") apiKey: String): Single<BufferForecast>
}
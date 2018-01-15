package me.arakmmis.weatherproject.ui.home

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.arakmmis.weatherproject.businesslogic.forecast.ForecastManager
import me.arakmmis.weatherproject.businesslogic.forecast.localdb.ForecastDb
import me.arakmmis.weatherproject.businesslogic.forecast.remote.ForecastRepo
import me.arakmmis.weatherproject.businesslogic.models.Forecast
import me.arakmmis.weatherproject.contracts.HomeContract
import me.arakmmis.weatherproject.utils.App
import me.arakmmis.weatherproject.utils.Cache
import me.arakmmis.weatherproject.utils.ConnectivityUtils

class HomePresenter(private val view: HomeContract.HomeView) : HomeContract.HomePresenter {

    private lateinit var forecastManager: ForecastManager

    init {
        checkInternet()
        getForecast()
    }

    private fun checkInternet() {
        forecastManager = if (ConnectivityUtils().isInternetOn(App.instance!!.applicationContext)) {
            ForecastRepo()
        } else {
            ForecastDb()
        }
    }

    private fun getForecast() {
        view.toggleLoading()

        forecastManager.getForecastFor(Cache.getCurrentCity())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { forecast: Forecast?, e: Throwable? ->
                    if (forecast != null) {
                        view.toggleLoading()
                        view.setViews(forecast)

                        if (ConnectivityUtils().isInternetOn(App.instance!!.applicationContext)) {
                            updateDB(forecast)
                        }
                    } else {
                        view.toggleLoading()
                        view.toast("Failed to load weather data")
                        Log.e("HP", e?.toString())
                    }
                }
    }

    private fun updateDB(forecast: Forecast) {
        val forestDB = ForecastDb()

        forestDB.deleteForecast(forecast.city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { result: String?, e: Throwable? ->
                    if (result != null) {
                        Log.d("HP", result)
                    } else {
                        Log.e("HP", e?.message)
                    }
                }

        forestDB.addForecast(forecast)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { result: String?, e: Throwable? ->
                    if (result != null) {
                        Log.d("HP", result)
                    } else {
                        Log.e("HP", e?.message)
                    }
                }
    }

    override fun selectedCity(city: String) {
        Cache.setCurrentCity(city)

        checkInternet()
        getForecast()
    }
}
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

    private val forecastManager: ForecastManager

    init {
        if (ConnectivityUtils().isInternetOn(App.instance!!.applicationContext)) {
            forecastManager = ForecastRepo()
        } else {
            forecastManager = ForecastDb()
        }

        getForecast()
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
                    } else {
                        view.toggleLoading()
                        view.toast("Failed to load weather data")
                        Log.e("HP:error", e?.toString())
                    }
                }
    }
}
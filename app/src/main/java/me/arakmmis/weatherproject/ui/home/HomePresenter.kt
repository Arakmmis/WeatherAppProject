package me.arakmmis.weatherproject.ui.home

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.arakmmis.weatherproject.businesslogic.forcast.ForecastManager
import me.arakmmis.weatherproject.businesslogic.forcast.localdb.ForecastDb
import me.arakmmis.weatherproject.businesslogic.forcast.remote.ForecastRepo
import me.arakmmis.weatherproject.businesslogic.pojos.Forecast
import me.arakmmis.weatherproject.contracts.HomeContract
import me.arakmmis.weatherproject.utils.App
import me.arakmmis.weatherproject.utils.Cache
import me.arakmmis.weatherproject.utils.ConnectivityUtils

class HomePresenter(val view: HomeContract.HomeView) : HomeContract.HomePresenter {

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
        forecastManager.getForecastFor(Cache.getCurrentCity())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { forecast: Forecast?, e: Throwable? ->
                    if (forecast != null) {

                    } else {

                    }
                }
    }
}
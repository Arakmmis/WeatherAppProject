package me.arakmmis.weatherproject.contracts

import me.arakmmis.weatherproject.businesslogic.models.Forecast

interface HomeContract {

    interface HomeView {
        fun toggleLoading()

        fun setViews(forecast: Forecast)

        fun toast(msg: String)
    }

    interface HomePresenter {
        fun selectedCity(city: String)
    }
}
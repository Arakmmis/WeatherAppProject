package me.arakmmis.weatherproject.businesslogic.forecast.localdb

import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.realm.Realm
import me.arakmmis.weatherproject.businesslogic.forecast.ForecastManager
import me.arakmmis.weatherproject.businesslogic.models.Forecast

class ForecastDb : ForecastManager {

    fun addForecast(forecast: Forecast): Single<String> = Single.create { received: SingleEmitter<String> ->
        getRealmInstance().executeTransaction { realm ->
            realm.insert(forecast)
        }

        received.onSuccess("Forecast Added Successfully")
        getRealmInstance().close()
    }

    override fun getForecastFor(city: String): Single<Forecast> = Single.create { received: SingleEmitter<Forecast> ->
        getRealmInstance().executeTransaction { realm ->
            val realmContact = realm.where(Forecast::class.java)
                    .equalTo("city", city)
                    .findFirst()

            received.onSuccess(realm.copyFromRealm(realmContact))
        }

        getRealmInstance().close()
    }

    fun deleteForecast(city: String): Single<String> = Single.create { received: SingleEmitter<String> ->
        getRealmInstance().executeTransaction { realm ->
            val realmContact = realm.where(Forecast::class.java)
                    .equalTo("city", city)
                    .findFirst()

            realmContact.deleteFromRealm()

            received.onSuccess("Forecast Deleted Successfully")
        }

        getRealmInstance().close()
    }

    private fun getRealmInstance(): Realm = Realm.getDefaultInstance()
}
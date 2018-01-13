package me.arakmmis.weatherproject.businesslogic.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Forecast(
        @PrimaryKey
        var city: String,

        var dt: RealmList<DateTime>,

        var temp: RealmList<Temperature>,

        var weather: RealmList<Weather>,

        var wind: RealmList<Wind>
) : RealmObject() {

    constructor() : this("", RealmList<DateTime>(), RealmList<Temperature>(), RealmList<Weather>(), RealmList<Wind>())
}
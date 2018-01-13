package me.arakmmis.weatherproject.businesslogic.models

import io.realm.RealmObject

open class Temperature(
        var temp: Double
) : RealmObject() {
    constructor() : this(0.0)
}
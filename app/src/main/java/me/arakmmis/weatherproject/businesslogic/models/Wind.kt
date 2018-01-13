package me.arakmmis.weatherproject.businesslogic.models

import io.realm.RealmObject

open class Wind(
        var speed: Double
) : RealmObject() {
    constructor() : this(0.0)
}
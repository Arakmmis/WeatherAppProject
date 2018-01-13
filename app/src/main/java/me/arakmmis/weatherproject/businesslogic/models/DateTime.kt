package me.arakmmis.weatherproject.businesslogic.models

import io.realm.RealmObject

open class DateTime(
        var dt: Int
) : RealmObject() {
    constructor() : this(0)
}
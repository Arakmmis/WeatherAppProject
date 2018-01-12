package me.arakmmis.weatherproject.utils

import android.app.Application
import com.orhanobut.hawk.Hawk

class App : Application() {
    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build()
//        Realm.init(this)
    }

    companion object {
        var instance: App? = null
    }
}
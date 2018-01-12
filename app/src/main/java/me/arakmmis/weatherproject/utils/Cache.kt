package me.arakmmis.weatherproject.utils

import com.orhanobut.hawk.Hawk

object Cache {

    fun setCurrentCity(currentCity: String) = Hawk.put(Const.CURRENT_CITY, currentCity)

    fun getCurrentCity(): String {
        if (!Hawk.contains(Const.CURRENT_CITY)) {
            return "Alexandria,EG"
        }

        return Hawk.get(Const.CURRENT_CITY)
    }

    fun removeCurrentCity() = Hawk.delete(Const.CURRENT_CITY)
}


package me.arakmmis.weatherproject.utils

interface Callback<in T> {

    fun onClick(item: T)
}
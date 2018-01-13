package me.arakmmis.weatherproject.utils

import android.content.Context
import android.graphics.Typeface

class Fonts(context: Context) {

    val vegurLight: Typeface
    val vegurBold: Typeface
    val vegurRegular: Typeface

    init {
        vegurBold = Typeface.createFromAsset(context.getAssets(), "fonts/Vegur-Bold.otf")
        vegurRegular = Typeface.createFromAsset(context.getAssets(), "fonts/Vegur-Regular.otf")
        vegurLight = Typeface.createFromAsset(context.getAssets(), "fonts/Vegur-Light.otf")
    }
}
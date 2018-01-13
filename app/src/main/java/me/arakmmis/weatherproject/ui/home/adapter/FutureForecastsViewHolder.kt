package me.arakmmis.weatherproject.ui.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.rv_item_future_forecasts.view.*
import me.arakmmis.weatherproject.businesslogic.models.Forecast
import java.util.*

class FutureForecastsViewHolder<in T>(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun setData(t: T) {
        val forecast: Forecast = t as Forecast

        itemView?.tv_temp?.text = String.format(Locale.US, "%.1f \u00B0C", forecast.temp[0].temp)

        Glide.with(itemView.context).load(forecast.weather[0].getDescPic()).into(itemView?.iv_temp_pic)

        itemView?.tv_day?.text = when (adapterPosition + 1) {
            1 -> "SUN"
            2 -> "MON"
            3 -> "TUES"
            4 -> "WED"
            5 -> "THURS"
            6 -> "FRI"
            7 -> "SAT"
            else -> "I have no idea"
        }
    }
}
package me.arakmmis.weatherproject.ui.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class FutureForecastAdapter<T>(val layout: Int, private var t: ArrayList<T>) :
        RecyclerView.Adapter<FutureForecastsViewHolder<T>>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FutureForecastsViewHolder<T> {
        val view = LayoutInflater.from(parent?.context).inflate(layout, parent, false)
        return FutureForecastsViewHolder<T>(view)
    }

    override fun onBindViewHolder(holder: FutureForecastsViewHolder<T>?, position: Int) {
        holder?.setData(t = t[position])
    }

    override fun getItemCount(): Int = t.size

    fun setData(data: List<T>) {
        t = data as ArrayList<T>
        notifyDataSetChanged()
    }
}
package me.arakmmis.weatherproject.ui.home

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import io.realm.RealmList
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.dialog_city_picker.view.*
import me.arakmmis.weatherproject.R
import me.arakmmis.weatherproject.businesslogic.models.Forecast
import me.arakmmis.weatherproject.businesslogic.models.Temperature
import me.arakmmis.weatherproject.businesslogic.models.Weather
import me.arakmmis.weatherproject.contracts.HomeContract
import me.arakmmis.weatherproject.ui.home.adapter.FutureForecastAdapter
import me.arakmmis.weatherproject.utils.Cache
import java.util.*
import kotlin.collections.ArrayList

class HomeActivity : AppCompatActivity(), HomeContract.HomeView {

    private lateinit var presenter: HomeContract.HomePresenter
    private var adapterFutureForecasts: FutureForecastAdapter<Forecast>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        presenter = HomePresenter(this)

        rv_future_forecasts.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_future_forecasts.isNestedScrollingEnabled = false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu);

        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_select -> {
                openChooserDialog()
                true
            }
            R.id.action_refresh -> {
                presenter.selectedCity(Cache.getCurrentCity())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openChooserDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_city_picker, null)
        val alertDialog = AlertDialog.Builder(this).setView(dialogView).create()

        when (Cache.getCurrentCity().toLowerCase()) {
            "alexandria, eg" -> dialogView.tv_alexandria.setTextColor(Color.BLACK)
            "istanbul, tr" -> dialogView.tv_istanbul.setTextColor(Color.BLACK)
            "tokyo, jp" -> dialogView.tv_tokyo.setTextColor(Color.BLACK)
            "berlin, de" -> dialogView.tv_berlin.setTextColor(Color.BLACK)
            "kuala lumpur, my" -> dialogView.tv_kuala_lumpur.setTextColor(Color.BLACK)
        }

        dialogView.tv_alexandria.setOnClickListener {
            presenter.selectedCity("Alexandria, EG")
            alertDialog.dismiss()
        }

        dialogView.tv_istanbul.setOnClickListener {
            presenter.selectedCity("Istanbul, TR")
            alertDialog.dismiss()
        }

        dialogView.tv_tokyo.setOnClickListener {
            presenter.selectedCity("Tokyo, JP")
            alertDialog.dismiss()
        }

        dialogView.tv_berlin.setOnClickListener {
            presenter.selectedCity("Berlin, DE")
            alertDialog.dismiss()
        }

        dialogView.tv_kuala_lumpur.setOnClickListener {
            presenter.selectedCity("Kuala Lumpur, MY")
            alertDialog.dismiss()
        }

        alertDialog.show()
    }

    override fun toggleLoading() {
        if (progress_bar.visibility == View.VISIBLE) {
            progress_bar.visibility = View.GONE
        } else {
            progress_bar.visibility = View.VISIBLE
        }
    }

    override fun toast(msg: String) {
        Toast.makeText(this@HomeActivity, msg, Toast.LENGTH_SHORT).show()
    }

    override fun setViews(forecast: Forecast) {
        tv_temp.text = String.format(Locale.US, "%.1f \u00B0C", forecast.temp[0].temp)
        tv_city_name.text = forecast.city
        tv_weather_desc.text = forecast.weather[0].desc

        Glide.with(this@HomeActivity).load(forecast.weather[0].getDescPic()).into(iv_temp_pic)

        tv_day.text = when (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
            1 -> "SUNDAY"
            2 -> "MONDAY"
            3 -> "TUESDAY"
            4 -> "WEDNESDAY"
            5 -> "THURSDAY"
            6 -> "FRIDAY"
            7 -> "SATURDAY"
            else -> "I have no idea"
        }

        if (adapterFutureForecasts == null) {
            adapterFutureForecasts = FutureForecastAdapter(
                    R.layout.rv_item_future_forecasts,
                    getForecastsList(forecast)
            )
            rv_future_forecasts.adapter = adapterFutureForecasts
        } else {
            adapterFutureForecasts!!.setData(getForecastsList(forecast))
        }
    }

    private fun getForecastsList(forecast: Forecast): ArrayList<Forecast> {
        val forecasts: ArrayList<Forecast> = ArrayList()

        for (i in 1 until forecast.dt.size) {
            val temp = RealmList<Temperature>()
            temp.add(forecast.temp[i])

            val weather = RealmList<Weather>()
            weather.add(forecast.weather[i])

            forecasts.add(
                    Forecast(city = "",
                            dt = RealmList(),
                            temp = temp,
                            weather = weather,
                            wind = RealmList()
                    )
            )
        }

        return forecasts
    }
}

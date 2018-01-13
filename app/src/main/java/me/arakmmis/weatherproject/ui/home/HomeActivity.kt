package me.arakmmis.weatherproject.ui.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.*
import me.arakmmis.weatherproject.R
import me.arakmmis.weatherproject.businesslogic.pojos.Forecast
import me.arakmmis.weatherproject.contracts.HomeContract

class HomeActivity : AppCompatActivity(), HomeContract.HomeView {

    private lateinit var presenter: HomeContract.HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        presenter = HomePresenter(this)
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
        city_name.text = forecast.cityName
        country_name.text = forecast.countryName
        weather_desc.text = forecast.weatherDesc
        wind_speed.text = forecast.windSpeed.toString()
        temp.text = forecast.temp.toString()
        date_time.text = forecast.dt.toString()
    }
}

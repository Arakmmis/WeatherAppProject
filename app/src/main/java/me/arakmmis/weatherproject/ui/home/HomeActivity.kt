package me.arakmmis.weatherproject.ui.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_home.*
import me.arakmmis.weatherproject.R
import me.arakmmis.weatherproject.businesslogic.models.Forecast
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
        city_name.text = forecast.city
        weather_desc.text = forecast.weather[0].desc
        wind_speed.text = forecast.wind[0].speed.toString()
        temp.text = forecast.temp[0].temp.toString()
        date_time.text = forecast.dt[4].dt.toString()

        Glide.with(this@HomeActivity).load(forecast.weather[4].getDescPic()).into(image)
    }
}

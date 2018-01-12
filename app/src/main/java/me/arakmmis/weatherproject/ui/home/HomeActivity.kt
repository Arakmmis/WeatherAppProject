package me.arakmmis.weatherproject.ui.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import me.arakmmis.weatherproject.R
import me.arakmmis.weatherproject.contracts.HomeContract

class HomeActivity : AppCompatActivity(), HomeContract.HomeView {

    lateinit var presenter: HomeContract.HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        presenter = HomePresenter(this)
    }
}

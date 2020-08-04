package com.test2.demo

import LocationHelper
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), HelperToActivityCallback {
    val locationHelper = LocationHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationHelper.requestLocationCallback()

    }

    override fun onLocationSuccess(locations: MutableList<Location>, lastLocation: Location) {
    }

    override fun onLocationFailed() {
    }
}

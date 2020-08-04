package com.test2.demo

import android.location.Location

interface HelperToActivityCallback {
    fun onLocationSuccess(
        locations: MutableList<Location>,
        lastLocation: Location
    )
    fun onLocationFailed()
}
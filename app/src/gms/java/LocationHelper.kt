import android.content.Context
import android.location.Location
import android.os.Looper
import com.google.android.gms.location.*
import com.test2.demo.HelperToActivityCallback

class LocationHelper(context: Context) {
    var callback: HelperToActivityCallback = context as HelperToActivityCallback
    var settingsClient = LocationServices.getSettingsClient(context)
    var fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    var mLocationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult?.let {
                callback.onLocationSuccess(it.locations, it.lastLocation)
                val locations: List<Location> =
                    locationResult.locations
                if (locations.isNotEmpty()) {
                    for (location in locations) {
                    }
                }
            }
        }

        override fun onLocationAvailability(locationAvailability: LocationAvailability?) {
            locationAvailability?.let {
                val flag: Boolean = locationAvailability.isLocationAvailable
            }
        }
    }

    var mLocationRequest = LocationRequest().apply {
        // set the interval for location updates, in milliseconds
        interval = 1000
        // set the priority of the request
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    fun requestLocationCallback() {
        fusedLocationProviderClient.requestLocationUpdates(mLocationRequest,
            mLocationCallback,
            Looper.getMainLooper())
    }
}
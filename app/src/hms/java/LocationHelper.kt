import android.content.Context
import android.content.IntentSender
import android.location.Location
import android.os.Looper
import com.huawei.hmf.tasks.Task
import com.huawei.hms.common.ApiException
import com.huawei.hms.common.ResolvableApiException
import com.huawei.hms.location.*
import com.test2.demo.HelperToActivityCallback
import java.lang.Exception

class LocationHelper(context: Context) {
    var callback:HelperToActivityCallback = context as HelperToActivityCallback
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
        needAddress = true
        // set the priority of the request
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }


    fun requestLocationCallback() {
        try {
            val builder = LocationSettingsRequest.Builder()

            builder.addLocationRequest(mLocationRequest)
            val locationSettingsRequest = builder.build()
            // check devices settings before request location updates.
            //Before requesting location update, invoke checkLocationSettings to check device settings.
            val locationSettingsResponseTask: Task<LocationSettingsResponse> = settingsClient.checkLocationSettings(locationSettingsRequest)

            locationSettingsResponseTask.addOnSuccessListener { locationSettingsResponse: LocationSettingsResponse? ->
                // request location updates
                fusedLocationProviderClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.getMainLooper())
                    .addOnSuccessListener {

                    }
                    .addOnFailureListener { e ->
                    }
            }
                .addOnFailureListener { e: Exception ->
                    when ((e as ApiException).statusCode) {
                        LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> try {
                            val rae = e as ResolvableApiException
                        } catch (sie: IntentSender.SendIntentException) {
                        }
                    }
                }

        } catch (e:Exception) {

        }
    }
}
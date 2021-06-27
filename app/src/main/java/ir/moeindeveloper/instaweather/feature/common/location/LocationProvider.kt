package ir.moeindeveloper.instaweather.feature.common.location

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import ir.moeindeveloper.instaweather.feature.common.preferences.Settings
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LocationProvider @Inject constructor(
    private val locationRequest: LocationRequest,
    private val client: FusedLocationProviderClient
) {

    @ExperimentalCoroutinesApi
    @SuppressLint("MissingPermission")
    fun fetchUpdates(): Flow<Settings.Location> = callbackFlow {

        val callBack = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                val location = locationResult.lastLocation
                val userLocation = Settings.Location(
                    latitude = location.latitude,
                    longitude = location.longitude
                )
                trySend(userLocation)
            }

        }

        client.requestLocationUpdates(locationRequest, callBack, Looper.getMainLooper())

        awaitClose { client.removeLocationUpdates(callBack) }
    }

    companion object {
        const val UPDATE_INTERVAL_SECS = 10L
        const val FASTEST_UPDATE_INTERVAL_SECS = 2L
        const val REQUEST_CHECK_SETTINGS: Int = 333
    }

}
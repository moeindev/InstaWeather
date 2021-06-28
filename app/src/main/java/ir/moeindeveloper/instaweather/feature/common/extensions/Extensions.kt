package ir.moeindeveloper.instaweather.feature.common.extensions

import android.content.Context
import androidx.compose.runtime.Composable
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability

@Composable
fun Context.GoogleAvailability(
    onInstalled: @Composable () -> Unit,
    onNotInstalled: @Composable () -> Unit,
    onNeedUpdate: @Composable () -> Unit) {
    when (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this)) {
        ConnectionResult.SUCCESS -> {
            onInstalled()
        }

        ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED -> {
            onNeedUpdate()
        }

        ConnectionResult.SERVICE_UPDATING -> {
            onNeedUpdate()
        }

        ConnectionResult.SERVICE_MISSING -> {
            onNotInstalled()
        }

        ConnectionResult.SERVICE_DISABLED -> {
            onNotInstalled()
        }

        ConnectionResult.SERVICE_INVALID -> {
            onNotInstalled()
        }
    }
}
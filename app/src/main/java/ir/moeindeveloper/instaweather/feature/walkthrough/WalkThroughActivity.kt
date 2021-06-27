package ir.moeindeveloper.instaweather.feature.walkthrough

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationSettingsResponse
import com.google.android.gms.location.LocationSettingsStatusCodes
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import dagger.hilt.android.AndroidEntryPoint
import ir.moeindeveloper.instaweather.R
import ir.moeindeveloper.instaweather.core.toast.toaster
import ir.moeindeveloper.instaweather.feature.common.location.LocationProvider.Companion.REQUEST_CHECK_SETTINGS
import ir.moeindeveloper.instaweather.feature.walkthrough.ui.SetupWalkThroughNavigation
import ir.moeindeveloper.instaweather.ui.theme.InstaWeatherTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@AndroidEntryPoint
class WalkThroughActivity : AppCompatActivity() {

    @Inject
    lateinit var settingsTask: Task<LocationSettingsResponse>

    @ExperimentalCoroutinesApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstaWeatherTheme {
                SetupWalkThroughNavigation {
                    finish()
                }
            }
        }

        val callBack = OnCompleteListener<LocationSettingsResponse> { task ->
            try {
                task.getResult(ApiException::class.java)
            } catch (ex: ApiException) {
                when (ex.statusCode) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                        val resolvableApiException = ex as ResolvableApiException
                        resolvableApiException.startResolutionForResult(this,REQUEST_CHECK_SETTINGS)
                    }

                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                        toaster(message = getString(R.string.gps_unavailable))
                    }
                }
            }
        }

        settingsTask.addOnCompleteListener(callBack)

    }
}
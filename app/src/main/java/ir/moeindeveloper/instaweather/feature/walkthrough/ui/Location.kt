package ir.moeindeveloper.instaweather.feature.walkthrough.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.accompanist.coil.rememberCoilPainter
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsResponse
import com.google.android.gms.location.LocationSettingsStatusCodes
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.skydoves.whatif.whatIfNotNull
import com.skydoves.whatif.whatIfNotNullWith
import ir.moeindeveloper.instaweather.MainActivity
import ir.moeindeveloper.instaweather.R
import ir.moeindeveloper.instaweather.core.extension.getActivity
import ir.moeindeveloper.instaweather.core.state.UiStatus
import ir.moeindeveloper.instaweather.feature.common.extensions.GoogleAvailability
import ir.moeindeveloper.instaweather.feature.common.location.LocationProvider
import ir.moeindeveloper.instaweather.feature.common.location.LocationProvider.Companion.REQUEST_CHECK_SETTINGS
import ir.moeindeveloper.instaweather.feature.common.preferences.Settings
import ir.moeindeveloper.instaweather.feature.walkthrough.viewModel.WalkThroughViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

const val findLocationNavDest: String = "find_location"


@ExperimentalCoroutinesApi
@Composable
fun FindLocation(viewModel: WalkThroughViewModel, mustFinishActivity: () -> Unit) {
    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    val useIp = rememberSaveable {
        mutableStateOf(true)
    }

    val gpsTurnedOn = rememberSaveable {
        mutableStateOf(LocationProvider.LocationSettingsStatus.WAITING)
    }

    val permissionGranted = remember {
        mutableStateOf(false)
    }


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()) { granted ->
        if (granted) {
            Log.d("LocationPermission", "Granted")
            scope.launch { permissionGranted.value = true }
        } else {
            Log.d("LocationPermission", "Denied")
            scope.launch { permissionGranted.value = false }
        }
    }

    SideEffect {
        if (permissionGranted.value) {
            val locationSettingsBuilder = LocationSettingsRequest.Builder().apply {
                addLocationRequest(viewModel.locationProvider.locationRequest)
            }

            val task : Task<LocationSettingsResponse> = LocationServices.getSettingsClient(context).checkLocationSettings(locationSettingsBuilder.build())

            task.addOnSuccessListener {
                Log.d("gps_available",it.locationSettingsStates?.isGpsUsable.toString())
                gpsTurnedOn.value = LocationProvider.LocationSettingsStatus.SUCCESS
            }

            task.addOnFailureListener { exception ->
                exception.printStackTrace()
                when((exception as ApiException).statusCode) {

                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                        gpsTurnedOn.value = LocationProvider.LocationSettingsStatus.WAITING
                        context.getActivity().whatIfNotNull { appCompatActivity ->
                            val resolvableApiException = exception as ResolvableApiException
                            resolvableApiException.startResolutionForResult(appCompatActivity, REQUEST_CHECK_SETTINGS)
                        }
                    }

                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                        gpsTurnedOn.value = LocationProvider.LocationSettingsStatus.ERROR
                    }

                    else -> {
                        gpsTurnedOn.value = LocationProvider.LocationSettingsStatus.ERROR
                        exception.printStackTrace()
                    }
                }
            }
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(5.dp)
        .verticalScroll(state = ScrollState(0)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround) {

        WalkThroughAnimation(R.raw.location)

        WalkThroughTitle(id = R.string.find_location)

        if (useIp.value) {
            UseIpLocation(viewModel) {
                useIp.value = false
            }
        }
        else {
            //check for permission
                context.GoogleAvailability(
                    onInstalled = {
                        when(PackageManager.PERMISSION_GRANTED) {
                            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) -> {
                                permissionGranted.value = true
                                when(gpsTurnedOn.value) {
                                    LocationProvider.LocationSettingsStatus.SUCCESS -> {
                                        UseGMSLocation(viewModel) {
                                            useIp.value = true
                                        }
                                    }

                                    LocationProvider.LocationSettingsStatus.WAITING -> {
                                        WaitingForGPS {
                                            useIp.value = true
                                        }
                                    }

                                    LocationProvider.LocationSettingsStatus.ERROR -> {
                                        GpsNotAvailable {
                                            useIp.value = true
                                        }
                                    }
                                }

                            }

                            else -> {
                                permissionGranted.value = true
                                launcher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
                                WaitingForGPS {
                                    useIp.value = true
                                }
                            }
                        }
                    },
                    onNotInstalled = {
                        GMSNotInstalled {
                            useIp.value = true
                        }
                    },
                    onNeedUpdate = {
                        GMSNeedUpdate {
                            useIp.value = true
                        }
                    }
                )
        }

        LocationButton(id = R.string.next_label) {
            viewModel.finishUpWalkThough()
            val mainHome = Intent(context, MainActivity::class.java)
            context.startActivity(mainHome)
            mustFinishActivity()
        }
    }
}

@Composable
fun UseIpLocation(viewModel: WalkThroughViewModel, onUseGMS: () -> Unit) {
    val locationState = viewModel.ipLocation.collectAsState()

    SideEffect {
        viewModel.loadIpLocation()
    }

    locationState.value.whatIfNotNull { state ->
        when(state.status) {
            UiStatus.LOADING -> {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    LocationText(R.string.find_location)
                    LocationButton(id = R.string.use_gps) {
                        onUseGMS()
                    }
                }
            }

            UiStatus.Failure -> {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    LocationText(id = R.string.ip_location_failed)
                    LocationButton(id = R.string.use_gps) {
                        onUseGMS()
                    }
                }
            }

            UiStatus.SUCCESS -> {
                state.data.whatIfNotNull { ipLocation ->
                    val location = Settings.Location(
                        latitude = ipLocation.lat,
                        longitude = ipLocation.lon
                    )

                    viewModel.saveLocation(location)

                    val locStr = "${ipLocation.query}, ${ipLocation.city}, ${ipLocation.country}"
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        LocationText(id = R.string.your_location_is)

                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp), horizontalArrangement = Arrangement.Center) {

                            Image(painter = rememberCoilPainter(request = "https://www.countryflags.io/${ipLocation.countryCode.lowercase()}/shiny/64.png")
                                , contentDescription = ipLocation.country)
                            
                            Text(text = locStr,
                                style = MaterialTheme.typography.body1,
                                color = MaterialTheme.colors.onPrimary)
                        }
                        LocationButton(id = R.string.use_gps) {
                            onUseGMS()
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalCoroutinesApi
@Composable
fun UseGMSLocation(viewModel: WalkThroughViewModel, onUseIpLocation: () -> Unit) {
    val locationState = viewModel.gmsLocation.collectAsState()

    locationState.value?.whatIfNotNullWith({ loc ->
        val location = Settings.Location(
            latitude = loc.latitude,
            longitude = loc.longitude
        )

        viewModel.saveLocation(location)

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            val locationStr = "${stringResource(id = R.string.your_location_is )} Latitude: ${loc.latitude}, Longitude: ${loc.longitude}"
            LocationText(string = locationStr)
            LocationButton(id = R.string.use_ip) {
                onUseIpLocation()
            }
        }
    }, {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            LocationText(id = R.string.find_location)
            LocationButton(id = R.string.use_ip) {
                onUseIpLocation()
            }
        }
    })
}


@Composable
fun GMSNotInstalled(onUseIpLocation: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        LocationText(id = R.string.install_gms)
        LocationButton(id = R.string.use_ip) {
            onUseIpLocation()
        }
    }
}

@Composable
fun GMSNeedUpdate(onUseIpLocation: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        LocationText(id = R.string.install_gms)
        LocationButton(id = R.string.use_ip) {
            onUseIpLocation()
        }
    }
}

@Composable
fun WaitingForGPS(onUseIpLocation: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        LocationText(id = R.string.waiting_for_turning_on_gps)
        LocationButton(id = R.string.use_ip) {
            onUseIpLocation()
        }
    }
}

@Composable
fun GpsNotAvailable(onUseIpLocation: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        LocationText(id = R.string.gps_unavailable)
        LocationButton(id = R.string.use_ip) {
            onUseIpLocation()
        }
    }
}

@Composable
fun LocationButton(@StringRes id: Int, onClick: () -> Unit) {
    Button(onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
        Text(text = stringResource(id = id))
    }
}

@Composable
fun LocationText(@StringRes id: Int = 0, string: String = "") {
    Text(text = if (id != 0) stringResource(id = id) else string,
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.onPrimary)
}

//@Preview(showSystemUi = true)
//@Composable
//fun FindLocationAnimPreview() {
//    InstaWeatherTheme {
//        Column {
//            FindLocationAnim()
//        }
//    }
//}
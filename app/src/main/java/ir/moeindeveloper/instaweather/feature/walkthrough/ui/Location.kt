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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.accompanist.coil.rememberCoilPainter
import com.skydoves.whatif.whatIfNotNull
import com.skydoves.whatif.whatIfNotNullWith
import ir.moeindeveloper.instaweather.MainActivity
import ir.moeindeveloper.instaweather.R
import ir.moeindeveloper.instaweather.core.state.UiStatus
import ir.moeindeveloper.instaweather.feature.common.extensions.GoogleAvailability
import ir.moeindeveloper.instaweather.feature.common.preferences.Settings
import ir.moeindeveloper.instaweather.feature.walkthrough.viewModel.WalkThroughViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

const val findLocationNavDest: String = "find_location"


@ExperimentalCoroutinesApi
@Composable
fun FindLocation(viewModel: WalkThroughViewModel, mustFinishActivity: () -> Unit) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()) { granted ->
        if (granted) {
            Log.d("LocationPermission", "Granted")
        } else {
            Log.d("LocationPermission", "Denied")
        }
    }

    val context = LocalContext.current

    val useIp = rememberSaveable {
        mutableStateOf(true)
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
                                UseGMSLocation(viewModel) {
                                    useIp.value = true
                                }
                            }

                            else -> {
                                launcher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
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

    LaunchedEffect(key1 = "RunOnRecompose") {
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

                        Row(modifier = Modifier.fillMaxWidth().padding(5.dp), horizontalArrangement = Arrangement.Center) {

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
    val locationState = viewModel.locationProvider.fetchUpdates().collectAsState(initial = null)

    locationState.value?.whatIfNotNullWith({ loc ->
        val location = Settings.Location(
            latitude = loc.latitude,
            longitude = loc.longitude
        )

        viewModel.saveLocation(location)

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)) {
            val locationStr = "${stringResource(id = R.string.your_location_is )} Latitude: ${loc.latitude}, Longitude: ${loc.longitude}"
            LocationText(string = locationStr)
            LocationButton(id = R.string.use_ip) {
                onUseIpLocation()
            }
        }
    }, {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)) {
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
        .padding(5.dp)) {
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
        .padding(5.dp)) {
        LocationText(id = R.string.install_gms)
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
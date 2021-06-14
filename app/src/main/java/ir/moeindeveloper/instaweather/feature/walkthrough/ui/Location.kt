package ir.moeindeveloper.instaweather.feature.walkthrough.ui

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import ir.moeindeveloper.instaweather.R
import ir.moeindeveloper.instaweather.feature.common.extensions.GoogleAvailability
import ir.moeindeveloper.instaweather.feature.walkthrough.viewModel.WalkThroughViewModel
const val FindLocationNavDest: String = "find_location"


@Composable
fun FindLocation(viewModel: WalkThroughViewModel, navController: NavController, onAskForLocation: () -> Unit) {
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

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween) {

        WalkThroughAnimation(R.raw.location)

        WalkThroughTitle(id = R.string.find_location)

        if (useIp.value) {
            UseIpLocation(viewModel) {
                useIp.value = false
            }
        } else {
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
    }
}

@Composable
fun UseIpLocation(viewModel: WalkThroughViewModel, onUseGMS: () -> Unit) {

}

@Composable
fun UseGMSLocation(viewModel: WalkThroughViewModel, onUseIpLocation: () -> Unit) {

}


@Composable
fun GMSNotInstalled(onUseIpLocation: () -> Unit) {

}

@Composable
fun GMSNeedUpdate(onUseIpLocation: () -> Unit) {

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
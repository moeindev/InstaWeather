package ir.moeindeveloper.instaweather.feature.walkthrough.ui

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.skydoves.whatif.whatIf
import ir.moeindeveloper.instaweather.MainActivity
import ir.moeindeveloper.instaweather.R
import ir.moeindeveloper.instaweather.feature.common.preferences.Settings
import kotlinx.coroutines.delay

const val splashDestName: String = "splash"


@Composable
fun Splash(settings: Settings, navController: NavController, onActivityStart: () -> Unit) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Image(painter = painterResource(id = R.drawable.ic_splash),
            contentDescription = "Logo",
            modifier = Modifier.size(144.dp))
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = "AtStart") {
        delay(2000)
        settings.firstTime.whatIf({
            navController.navigate(route = languageDestName)
        }, {
            val mainHome = Intent(context, MainActivity::class.java)
            context.startActivity(mainHome)
            onActivityStart()
        })
    }
}
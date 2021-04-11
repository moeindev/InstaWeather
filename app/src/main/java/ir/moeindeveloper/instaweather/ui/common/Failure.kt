package ir.moeindeveloper.instaweather.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieAnimationSpec
import com.airbnb.lottie.compose.rememberLottieAnimationState
import ir.moeindeveloper.instaweather.R
import ir.moeindeveloper.instaweather.ui.theme.InstaWeatherTheme

@Composable
fun Failed(reason: String, onRetryClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxHeight(),
    verticalArrangement = Arrangement.Center) {
        val animSpec = remember { LottieAnimationSpec.RawRes(R.raw.failure) }

        val animState = rememberLottieAnimationState(autoPlay = true, repeatCount = Int.MAX_VALUE)

        LottieAnimation(
            spec = animSpec,
            animationState = animState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .height(250.dp))

        Text(text = reason,
            color = MaterialTheme.colors.onPrimary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp))

        Button(onClick = { onRetryClick() },modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
            Text(text = "Retry!")
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun FailedPreview() {
    InstaWeatherTheme {
        Failed(reason = "Fucked up!") {

        }
    }
}
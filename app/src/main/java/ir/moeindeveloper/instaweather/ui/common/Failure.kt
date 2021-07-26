package ir.moeindeveloper.instaweather.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import ir.moeindeveloper.instaweather.R
import ir.moeindeveloper.instaweather.ui.theme.InstaWeatherTheme

@Composable
fun Failed(reason: String, onRetryClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxHeight(),
    verticalArrangement = Arrangement.Center) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.failure))
        val progress by animateLottieCompositionAsState(composition)

        LottieAnimation(
            composition,
            progress,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .height(250.dp)
        )

        Text(text = reason,
            color = MaterialTheme.colors.onPrimary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp))

        Button(onClick = { onRetryClick() },modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun FailedPreview() {
    InstaWeatherTheme {
        Failed(reason = "Network failure!") {

        }
    }
}
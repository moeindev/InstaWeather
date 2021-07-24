package ir.moeindeveloper.instaweather.feature.walkthrough.ui

import androidx.annotation.RawRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun WalkThroughTitle(@StringRes id: Int) {
    Text(text = stringResource(id = id),
        modifier = Modifier.fillMaxWidth(),
        style = MaterialTheme.typography.h5,
        color = MaterialTheme.colors.onPrimary,
        textAlign = TextAlign.Center)
}

@Composable
fun WalkThroughAnimation(@RawRes id: Int) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(id))
    val progress by animateLottieCompositionAsState(composition)

    LottieAnimation(
        composition,
        progress,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(5.dp)
    )
}
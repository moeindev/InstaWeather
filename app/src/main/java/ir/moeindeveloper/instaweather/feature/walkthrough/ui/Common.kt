package ir.moeindeveloper.instaweather.feature.walkthrough.ui

import androidx.annotation.RawRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieAnimationSpec
import com.airbnb.lottie.compose.rememberLottieAnimationState


@Composable
fun WalkThroughTitle(@StringRes id: Int) {
    Text(text = stringResource(id = id),
        modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp).fillMaxWidth(),
        style = MaterialTheme.typography.h5,
        color = MaterialTheme.colors.onPrimary)
}

@Composable
fun WalkThroughAnimation(@RawRes id: Int) {
    val animSpec = remember { LottieAnimationSpec.RawRes(id) }

    val animState = rememberLottieAnimationState(autoPlay = true, repeatCount = Int.MAX_VALUE)

    LottieAnimation(
        spec = animSpec,
        animationState = animState,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(5.dp))
}
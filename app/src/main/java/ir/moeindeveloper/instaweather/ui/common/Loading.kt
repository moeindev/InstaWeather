package ir.moeindeveloper.instaweather.ui.common

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieAnimationSpec
import com.airbnb.lottie.compose.rememberLottieAnimationState
import ir.moeindeveloper.instaweather.R

@Preview(showSystemUi = true)
@Composable
fun Loader() {

    val animSpec = remember { LottieAnimationSpec.RawRes(R.raw.loading) }

    val animState = rememberLottieAnimationState(autoPlay = true, repeatCount = Int.MAX_VALUE)

    LottieAnimation(
        spec = animSpec,
        animationState = animState,
        modifier = Modifier.fillMaxHeight().fillMaxWidth())
}
package ir.moeindeveloper.instaweather.feature.home.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieAnimationSpec
import com.airbnb.lottie.compose.rememberLottieAnimationState
import ir.moeindeveloper.instaweather.feature.common.util.WeatherType



@Composable
fun HomeBackground(isNight: Boolean, type: WeatherType){
    val animationId = if (isNight) type.nightAnim else type.dayAnim

    val animSpec = remember { LottieAnimationSpec.RawRes(animationId) }

    val animState = rememberLottieAnimationState(autoPlay = true, repeatCount = Int.MAX_VALUE)

    LottieAnimation(
        spec = animSpec,
        animationState = animState,
        modifier = Modifier.size(350.dp)
            .padding(10.dp))
}

@Preview(name = "Clear Sky",showSystemUi = true)
@Composable
fun HomeBGClearSky() {
    HomeBackground(isNight = false,type = WeatherType.CLEAR_SKY)
}


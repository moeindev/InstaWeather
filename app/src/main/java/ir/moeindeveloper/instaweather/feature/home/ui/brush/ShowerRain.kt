package ir.moeindeveloper.instaweather.feature.home.ui.brush

import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import ir.moeindeveloper.instaweather.feature.common.util.WeatherType

@Composable
fun rain(shower: Boolean = false): Brush {
    val infiniteAnim = rememberInfiniteTransition()

    val yAnim = infiniteAnim.animateFloat(
        initialValue = 50f,
        targetValue = 1400f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                20000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )


    return Brush.verticalGradient(
        if (shower) WeatherType.SHOWER_RAIN.colors else WeatherType.RAIN.colors,
        endY = yAnim.value
        )
}
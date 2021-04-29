package ir.moeindeveloper.instaweather.feature.home.ui.brush

import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import ir.moeindeveloper.instaweather.feature.common.util.WeatherType

@Composable
fun snow(): Brush {
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
        WeatherType.SNOW.colors,
        endY = yAnim.value
    )
}
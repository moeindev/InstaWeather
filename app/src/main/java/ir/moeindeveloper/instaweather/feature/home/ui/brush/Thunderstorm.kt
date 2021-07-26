package ir.moeindeveloper.instaweather.feature.home.ui.brush

import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import ir.moeindeveloper.instaweather.feature.common.util.WeatherType

@Composable
fun thunderstorm(): Brush {
    val infiniteAnim = rememberInfiniteTransition()
    val xAnim = infiniteAnim.animateFloat(
        initialValue = 100f,
        targetValue = 999f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                20000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    val yAnim = infiniteAnim.animateFloat(
        initialValue = 100f,
        targetValue = 999f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                20000,
                easing = LinearOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    return Brush.linearGradient(
        colors = WeatherType.THUNDERSTORM.colors,
        start = Offset(0f,0f),
        end = Offset(xAnim.value,yAnim.value)
    )
}
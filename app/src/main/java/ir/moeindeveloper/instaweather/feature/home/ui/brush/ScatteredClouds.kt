package ir.moeindeveloper.instaweather.feature.home.ui.brush

import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import ir.moeindeveloper.instaweather.feature.common.util.WeatherType

@Composable
fun scatteredClouds(): Brush {
    val infiniteAnim = rememberInfiniteTransition()
    val xAnim = infiniteAnim.animateFloat(
        initialValue = 350f,
        targetValue = 800f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                20000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    val yAnim = infiniteAnim.animateFloat(
        initialValue = 200f,
        targetValue = 700f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                20000,
                easing = LinearOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    return Brush.linearGradient(
        colors = WeatherType.SCATTERED_CLOUDS.colors,
        start = Offset(xAnim.value,40f),
        end = Offset(40f,yAnim.value)
    )
}
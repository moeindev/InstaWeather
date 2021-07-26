package ir.moeindeveloper.instaweather.feature.home.ui.brush

import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import ir.moeindeveloper.instaweather.feature.common.util.WeatherType


@Composable
fun clearSky(isNight: Boolean): Brush {
    val infiniteAnim = rememberInfiniteTransition()
    
    val radius = infiniteAnim.animateFloat(
        initialValue = 900f,
        targetValue = 1900f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                20000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    val colors = if (isNight) WeatherType.CLEAR_SKY.colors else WeatherType.CLEAR_SKY.colors.take(3)

    return Brush.radialGradient(colors,
            Offset(0f,0f),
            radius.value,
            TileMode.Clamp)

}
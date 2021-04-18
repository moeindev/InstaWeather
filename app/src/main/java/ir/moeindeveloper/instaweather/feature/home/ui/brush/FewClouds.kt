package ir.moeindeveloper.instaweather.feature.home.ui.brush

import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import ir.moeindeveloper.instaweather.feature.common.util.WeatherType

@Composable
fun fewClouds(): Brush {
    val infiniteAnim = rememberInfiniteTransition()


    val xAnim = infiniteAnim.animateFloat(
        initialValue = 350f,
        targetValue = 1450f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                20000,
                easing = LinearOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    val yAnim = infiniteAnim.animateFloat(
        initialValue = 300f,
        targetValue = 1300f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                20000,
                easing = LinearOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )


    return Brush.linearGradient(
        WeatherType.FEW_CLOUDS.colors,
        start = Offset(xAnim.value, 0f),
        end = Offset(0f, yAnim.value),
        TileMode.Clamp
    )

}
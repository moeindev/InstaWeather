package ir.moeindeveloper.instaweather.feature.common.util

import androidx.compose.ui.graphics.Color
import ir.moeindeveloper.instaweather.R

enum class WeatherType(val colors: List<Color>,
                       val icon: Int,
                       val dayAnim: Int,
                       val nightAnim: Int) {
    CLEAR_SKY(
        colors = listOf(
            Color(0xFFffd241),
            Color(0xFFffff00),
            Color(0xFFf3c955)
        ),
        icon = R.drawable.w_ic_clear_sky,
        dayAnim = R.raw.clear_sky_day,
        nightAnim = R.raw.clear_sky_night
    ),
    FEW_CLOUDS(
        colors = listOf(
            Color(0xFF97bcd4),
            Color(0xFFa2bac9),
            Color(0xFF6d99b6)
        ),
        icon = R.drawable.w_ic_few_clouds,
        dayAnim = R.raw.few_clouds_day,
        nightAnim = R.raw.few_clouds_night
    ),
    SCATTERED_CLOUDS(
        colors = listOf(
            Color(0xFFb4b4b4),
            Color(0xFFb7b7b2),
            Color(0xFF94948d)
        ),
        icon = R.drawable.w_ic_scattered_clouds,
        dayAnim = R.raw.scattered_clouds_day,
        nightAnim =R.raw.scattered_clouds_night
    ),
    BROKEN_CLOUDS(
        colors = listOf(
            Color(0xFFb9d6df),
            Color(0xFFb4d8e4),
            Color(0xFF78b9ce)
        ),
        icon = R.drawable.w_ic_broken_clouds,
        dayAnim = R.raw.broken_clouds_day,
        nightAnim = R.raw.broken_clouds_night
    ),
    SHOWER_RAIN(
        colors = listOf(
            Color(0xFF3f6c8f),
            Color(0xFF356d99),
            Color(0xFF325672)
        ),
        icon = R.drawable.w_ic_shower_rain,
        dayAnim = R.raw.shower_rain_day,
        nightAnim = R.raw.shower_rain_night
    ),
    RAIN(
        colors = listOf(
            Color(0xFF5087b2),
            Color(0xFF3f6c8f),
            Color(0xFF4682b4)
        ),
        icon = R.drawable.w_ic_rain,
        dayAnim = R.raw.rain_day,
        nightAnim = R.raw.rain_night
    ),

    THUNDERSTORM(
        colors = listOf(
            Color(0xFF5f646c),
            Color(0xFF5d646e),
            Color(0xFF696969)
        ),
        icon = R.drawable.w_ic_thunderstorm,
        dayAnim = R.raw.thunderstorm_day,
        nightAnim = R.raw.thunderstorm_night
    ),
    SNOW(
        colors = listOf(
            Color(0xFFffffff),
            Color(0xFFf1eeeb),
            Color(0xFFf5f5f5)
        ),
        icon = R.drawable.w_ic_snow,
        dayAnim = R.raw.snow_day,
        nightAnim = R.raw.snow_night
    ),
    MIST(
        colors = listOf(
            Color(0xFF5e5551),
            Color(0xFF4b4441),
            Color(0xFF4a4442)
        ),
        icon = R.drawable.w_ic_mist,
        dayAnim = R.raw.mist_day,
        nightAnim = R.raw.mist_night
    )
}
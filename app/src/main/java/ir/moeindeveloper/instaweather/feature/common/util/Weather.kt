package ir.moeindeveloper.instaweather.feature.common.util

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.compose.ui.graphics.Color
import ir.moeindeveloper.instaweather.R
import ir.moeindeveloper.instaweather.core.log.appLog

enum class WeatherType(val colors: List<Color>,
                       val icon: Int) {
    CLEAR_SKY(
        colors = listOf(
            Color(0xFFffff00),
            Color(0xFFffd241),
            Color(0xFFf3c955),
            Color(0xFFFF964A),
            Color(0xFFFF3B42),
            Color.Transparent
        ),
        icon = R.drawable.w_ic_clear_sky
    ),
    FEW_CLOUDS(
        colors = listOf(
            Color(0xFFa2bac9),
            Color(0xFF97bcd4),
            Color(0xFF6d99b6),
            Color.Transparent
        ),
        icon = R.drawable.w_ic_few_clouds
    ),
    SCATTERED_CLOUDS(
        colors = listOf(
            Color(0xFFb4b4b4),
            Color(0xFFb7b7b2),
            Color(0xFF94948d),
            Color.Transparent
        ),
        icon = R.drawable.w_ic_scattered_clouds
    ),
    BROKEN_CLOUDS(
        colors = listOf(
            Color(0xFF78b9ce),
            Color(0xFFb9d6df),
            Color(0xFFb4d8e4)
        ),
        icon = R.drawable.w_ic_broken_clouds
    ),
    SHOWER_RAIN(
        colors = listOf(
            Color(0xFF3f6c8f),
            Color(0xFF356d99),
            Color(0xFF325672),
            Color.Transparent
        ),
        icon = R.drawable.w_ic_shower_rain
    ),
    RAIN(
        colors = listOf(
            Color(0xFF5087b2),
            Color(0xFF3f6c8f),
            Color(0xFF4682b4),
            Color.Transparent
        ),
        icon = R.drawable.w_ic_rain
    ),

    THUNDERSTORM(
        colors = listOf(
            Color(0xFF5f646c),
            Color(0xFF5d646e),
            Color(0xFF696969),
            Color.Transparent
        ),
        icon = R.drawable.w_ic_thunderstorm
    ),
    SNOW(
        colors = listOf(
            Color(0xFFffffff),
            Color(0xFFf1eeeb),
            Color(0xFFf5f5f5),
            Color.Transparent
        ),
        icon = R.drawable.w_ic_snow
    ),
    MIST(
        colors = listOf(
            Color(0xFF5e5551),
            Color(0xFF4b4441),
            Color(0xFF4a4442),
            Color.Transparent
        ),
        icon = R.drawable.w_ic_mist
    )
}

//TODO Change for other Units
fun Double.toStringTemp(): String =
    "${this.toInt()}??"

fun String.weatherType(): WeatherType {
    return when(this) {
        "01d" -> {
            //Clear sky
            WeatherType.CLEAR_SKY
        }

        "01n" -> {
            //Clear sky
            WeatherType.CLEAR_SKY
        }

        "02d" -> {
            //Few clouds
            WeatherType.FEW_CLOUDS
        }

        "02n" -> {
            //Few clouds
            WeatherType.FEW_CLOUDS
        }

        "03d" -> {
            //scattered clouds
            WeatherType.SCATTERED_CLOUDS
        }

        "03n" -> {
            //scattered clouds
            WeatherType.SCATTERED_CLOUDS
        }


        "04d" -> {
            //broken clouds
            WeatherType.SCATTERED_CLOUDS
        }

        "04n" -> WeatherType.BROKEN_CLOUDS

        "09d" -> {
            //shower rain
            WeatherType.SHOWER_RAIN
        }


        "09n" -> {
            //shower rain
            WeatherType.SHOWER_RAIN
        }

        "10d" -> {
            //rain
            WeatherType.RAIN
        }

        "10n" -> {
            WeatherType.RAIN
        }

        "11d" -> {
            //thunderstorm
            WeatherType.THUNDERSTORM
        }

        "11n" -> {
            //thunderstorm
            WeatherType.THUNDERSTORM
        }

        "13d" -> {
            //snow
            WeatherType.SNOW
        }

        "13n" -> {
            //snow
            WeatherType.SNOW
        }


        "50d" -> {
            //mist
            WeatherType.MIST
        }

        "50n" -> {
            //mist
            WeatherType.MIST
        }

        else -> {
            appLog { "Illegal" }
            appLog { "Icon: $this" }
            WeatherType.CLEAR_SKY
        }
    }
}
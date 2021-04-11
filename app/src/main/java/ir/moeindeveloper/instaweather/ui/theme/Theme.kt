package ir.moeindeveloper.instaweather.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = secondaryColor,
    primaryVariant = secondaryDark,
    secondary = primaryColor,
    background = secondaryDark,
    onPrimary = onSecondary,
    onSecondary = onSecondary,
    onBackground = onSecondary
)

private val LightColorPalette = lightColors(
    primary = primaryColor,
    primaryVariant = primaryDark,
    secondary = secondaryColor,
    background = primaryDark,
    onPrimary = onPrimary,
    onSecondary = onPrimary,
    onBackground = onPrimary
)

@Composable
fun InstaWeatherTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
    )
}
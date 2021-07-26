package ir.moeindeveloper.instaweather.feature.home.ui.brush

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import ir.moeindeveloper.instaweather.feature.common.util.WeatherType

@Composable
fun WeatherType.getBrush(isNight: Boolean): Brush {
    return when(this) {
        WeatherType.CLEAR_SKY -> clearSky(isNight)
        WeatherType.FEW_CLOUDS -> fewClouds()
        WeatherType.SCATTERED_CLOUDS -> scatteredClouds()
        WeatherType.SHOWER_RAIN -> rain(true)
        WeatherType.RAIN -> rain()
        WeatherType.THUNDERSTORM -> thunderstorm()
        WeatherType.SNOW -> snow()
        WeatherType.MIST -> mist()
        else -> clearSky(isNight)
    }
}


@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ClearSkyPreview() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(clearSky(true)))
}

@Preview
@Composable
fun FewCloudsPreview() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(fewClouds()))
}

@Preview
@Composable
fun ScatteredCloudsPreview() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(scatteredClouds()))
}

@Preview
@Composable
fun ShowerRainPreview() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(rain()))
}

@Preview
@Composable
fun ThunderStormPreview() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(thunderstorm()))
}
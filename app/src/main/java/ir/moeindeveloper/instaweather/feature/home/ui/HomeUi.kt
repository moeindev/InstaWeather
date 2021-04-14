package ir.moeindeveloper.instaweather.feature.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import ir.moeindeveloper.instaweather.core.log.appLog
import ir.moeindeveloper.instaweather.core.state.UiState
import ir.moeindeveloper.instaweather.core.state.UiStatus
import ir.moeindeveloper.instaweather.feature.common.entity.WeatherInfoBox
import ir.moeindeveloper.instaweather.feature.common.util.toStringTemp
import ir.moeindeveloper.instaweather.feature.common.util.weatherType
import ir.moeindeveloper.instaweather.feature.common.viewModel.WeatherViewModel
import ir.moeindeveloper.instaweather.ui.common.Failed
import ir.moeindeveloper.instaweather.ui.common.Loader

const val homeDestName: String = "home_screen"

@Composable
fun HomeScreen(mainViewModel: WeatherViewModel,navController: NavController) {

    val weatherData = mainViewModel.data.collectAsState()

    weatherData.value?.let { data ->
        data.appLog { "data = $data" }
        when(data.status) {
            UiStatus.LOADING -> Loader()
            UiStatus.Failure -> Failed(reason = data.errorMessage ?: "") {
                mainViewModel.getWeatherData()
            }
            UiStatus.SUCCESS -> {
                data.data?.let { weatherInfo ->
                    data.appLog { "data from repo -> $data" }
                    HomeSuccess(data = weatherInfo)
                }
            }
        }
    }
}

@Composable
fun HomeSuccess(data: WeatherInfoBox) {
    Column(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly) {
        //TODO add lastUpdate
        HomeHeader(lastUpdate = "Someday on March 27", city = data.timezone) {
            //TODO open settings
        }

        HomeBackground(
            isNight = false,
            type = data.current.target.weather[0].icon.weatherType()
        )

        HomeCurrent(
            description = data.current.target.weather[0].description,
            temperature = data.current.target.temp.toStringTemp(),
            //TODO add units
            unit = "C",
            feelsLike = data.current.target.feelsLike.toStringTemp(),
            dewPoint = data.current.target.feelsLike.toStringTemp()
        )

        HourlyContainer(hours = data.hourly)
    }
}
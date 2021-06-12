package ir.moeindeveloper.instaweather.feature.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ir.moeindeveloper.instaweather.core.log.appLog
import ir.moeindeveloper.instaweather.core.state.UiStatus
import ir.moeindeveloper.instaweather.feature.common.entity.WeatherInfoBox
import ir.moeindeveloper.instaweather.feature.common.util.toStringTemp
import ir.moeindeveloper.instaweather.feature.common.util.weatherType
import ir.moeindeveloper.instaweather.feature.common.viewModel.WeatherViewModel
import ir.moeindeveloper.instaweather.feature.home.ui.brush.getBrush
import ir.moeindeveloper.instaweather.ui.common.Failed
import ir.moeindeveloper.instaweather.ui.common.Loader

const val homeDestName: String = "home_screen"

@Composable
fun HomeScreen(mainViewModel: WeatherViewModel,navController: NavController) {

    val weatherData = mainViewModel.data.collectAsState()

    LaunchedEffect(key1 = "FirstTime") {
        mainViewModel.getWeatherData()
    }

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
        .fillMaxWidth()
        .background(
            data.current.target.weather[0].icon
                .weatherType()
                //TODO add dark mode preferences here
                .getBrush(isNight = true)
        ),
        verticalArrangement = Arrangement.SpaceEvenly) {
        //TODO add lastUpdate
        HomeHeader(lastUpdate = "Someday on March 27", city = data.timezone) {
            //TODO open settings
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(250.dp)
            .height(350.dp))
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
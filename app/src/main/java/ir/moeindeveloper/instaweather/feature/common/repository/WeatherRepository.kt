package ir.moeindeveloper.instaweather.feature.common.repository

import ir.moeindeveloper.instaweather.core.state.BackgroundState
import ir.moeindeveloper.instaweather.feature.common.Constant
import ir.moeindeveloper.instaweather.feature.common.entity.WeatherInfoBox
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    fun retrieveWeatherData(
        latitude: Double? = null,
        longitude: Double? = null,
        exclude: String? = Constant.DEFAULT_EXCLUDE,
        units: String? = Constant.DEFAULT_UNITS,
        appId: String? = Constant.weatherToken,
    ): Flow<BackgroundState<WeatherInfoBox>>


}
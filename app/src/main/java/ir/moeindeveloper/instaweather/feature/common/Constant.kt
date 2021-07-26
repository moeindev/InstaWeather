package ir.moeindeveloper.instaweather.feature.common

import ir.moeindeveloper.instaweather.BuildConfig

object Constant {

    const val weatherToken: String = BuildConfig.WEATHER_API_KEY

    object QueryParams {
        const val lat: String = "lat"
        const val lon: String = "lon"
        const val units: String = "units"
        const val exclude: String = "exclude"
        const val appID: String = "appid"
    }

    const val DEFAULT_EXCLUDE = "minutely"
    const val DEFAULT_UNITS = "metric"

}
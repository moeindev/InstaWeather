package ir.moeindeveloper.instaweather.feature.common.repository

import io.objectbox.BoxStore
import ir.moeindeveloper.instaweather.core.platform.repository.BaseRepository
import ir.moeindeveloper.instaweather.feature.common.entity.WeatherInfoBox
import ir.moeindeveloper.instaweather.feature.common.service.WeatherService
import kotlinx.coroutines.flow.Flow

class WeatherRepositoryImpl(private val service: WeatherService,
                            private val store: BoxStore): BaseRepository(), WeatherRepository {
    override fun retrieveWeatherData(
        latitude: Double?,
        longitude: Double?,
        exclude: String?,
        units: String?,
        appId: String?,
        onSuccess: () -> Unit,
        onError: (message: String) -> Unit,
        onException: (exception: Throwable) -> Unit
    ): Flow<WeatherInfoBox> = networkAndCacheRequest(
        store = store,
        request = { service.oneCallApi(lat = latitude,
            lon = longitude, exclude = exclude,units = units,appID = appId ) } ,
        onSuccess = { onSuccess() },
        onError = { onError(it) },
        onException = { onException(it) }
    )
}
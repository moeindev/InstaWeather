package ir.moeindeveloper.instaweather.feature.common.viewModel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.moeindeveloper.instaweather.core.extension.transformToFlow
import ir.moeindeveloper.instaweather.core.log.appLog
import ir.moeindeveloper.instaweather.core.platform.viewModel.BaseViewModel
import ir.moeindeveloper.instaweather.core.state.BackgroundState
import ir.moeindeveloper.instaweather.core.state.BackgroundStatus
import ir.moeindeveloper.instaweather.core.state.UiState
import ir.moeindeveloper.instaweather.feature.common.Constant
import ir.moeindeveloper.instaweather.feature.common.entity.WeatherInfoBox
import ir.moeindeveloper.instaweather.feature.common.repository.WeatherRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepository): BaseViewModel() {

    private val weatherLoadingChannel: Channel<HashMap<String,Any>> = Channel()

    val data: StateFlow<UiState<WeatherInfoBox>?> = scope {
        weatherLoadingChannel.transformToFlow { channel ->
            emitAll(
                repository.retrieveWeatherData(
                    latitude = channel[lat] as Double,
                    longitude = channel[lon] as Double,
                    exclude = channel[exclude] as String,
                    units = channel[units] as String
                ).convertToUiState()
            )
        }
    }

    init {
        appLog { "initial value of weather data = $data" }
        getWeatherData()
    }

    fun getWeatherData() {
        //TODO get pre-saved data from preferences!
        //for now just use the constants
        viewModelScope.launch {
            weatherLoadingChannel.send(
                hashMapOf(
                    //TODO change these values to dynamic values
                    lat to 34.3277,
                    lon to 47.0778,
                    exclude to Constant.DEFAULT_EXCLUDE,
                    units to Constant.DEFAULT_UNITS
                )
            )
        }
    }

    private fun Flow<BackgroundState<WeatherInfoBox>>.convertToUiState(): Flow<UiState<WeatherInfoBox>> = map { value ->
        when(value.status) {
            BackgroundStatus.LOADING ->  UiState.loading()
            BackgroundStatus.SUCCESS -> UiState.success(value.data)
            BackgroundStatus.ERROR ->  UiState.failure(value.errorMessage)
            BackgroundStatus.EXCEPTION -> UiState.failure(value.errorMessage)
        }
    }

    companion object {
        const val lat: String = Constant.QueryParams.lat
        const val lon: String = Constant.QueryParams.lon
        const val units: String = Constant.QueryParams.units
        const val exclude: String = Constant.QueryParams.exclude
    }
}
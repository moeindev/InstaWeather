package ir.moeindeveloper.instaweather.feature.common.viewModel

import androidx.lifecycle.viewModelScope
import com.skydoves.whatif.whatIfNotNull
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.moeindeveloper.instaweather.core.extension.transformToFlow
import ir.moeindeveloper.instaweather.core.log.appLog
import ir.moeindeveloper.instaweather.core.platform.viewModel.BaseViewModel
import ir.moeindeveloper.instaweather.core.state.BackgroundState
import ir.moeindeveloper.instaweather.core.state.BackgroundStatus
import ir.moeindeveloper.instaweather.core.state.UiState
import ir.moeindeveloper.instaweather.feature.common.Constant
import ir.moeindeveloper.instaweather.feature.common.entity.IpLocation
import ir.moeindeveloper.instaweather.feature.common.entity.WeatherInfoBox
import ir.moeindeveloper.instaweather.feature.common.preferences.Settings
import ir.moeindeveloper.instaweather.feature.common.repository.IpLocationRepository
import ir.moeindeveloper.instaweather.feature.common.repository.WeatherRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepository,
                                           val settings: Settings
                                           ): BaseViewModel() {

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


    fun getWeatherData() {
        //Done get pre-saved data from preferences!
        //for now just use the constants
        viewModelScope.launch {
            var latitude = 34.3277
            var longitude = 47.0778

            settings.location.whatIfNotNull { location ->
                latitude = location.latitude
                longitude = location.longitude
            }
            weatherLoadingChannel.send(
                hashMapOf(
                    //Done change these values to dynamic values
                    lat to latitude,
                    lon to longitude,
                    exclude to Constant.DEFAULT_EXCLUDE,
                    units to Constant.DEFAULT_UNITS
                )
            )
        }
    }

    companion object {
        const val lat: String = Constant.QueryParams.lat
        const val lon: String = Constant.QueryParams.lon
        const val units: String = Constant.QueryParams.units
        const val exclude: String = Constant.QueryParams.exclude
    }
}
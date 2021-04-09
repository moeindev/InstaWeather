package ir.moeindeveloper.instaweather.feature.common.viewModel

import androidx.compose.ui.unit.Constraints
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.moeindeveloper.instaweather.core.extension.transformToFlow
import ir.moeindeveloper.instaweather.core.platform.viewModel.BaseViewModel
import ir.moeindeveloper.instaweather.core.state.UiState
import ir.moeindeveloper.instaweather.feature.common.Constant
import ir.moeindeveloper.instaweather.feature.common.entity.WeatherInfoBox
import ir.moeindeveloper.instaweather.feature.common.repository.WeatherRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepository): BaseViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> get() = _uiState

    private val weatherLoadingChannel: Channel<HashMap<String,Any>> = Channel()

    val data: StateFlow<WeatherInfoBox?> = scope {
        weatherLoadingChannel.transformToFlow { channel ->
            emitAll(
                repository.retrieveWeatherData(
                    latitude = channel[lat] as Double,
                    longitude = channel[lon] as Double,
                    exclude = channel[exclude] as String,
                    units = channel[units] as String,
                    onSuccess = { updateUiState(UiState.Success) },
                    onError = { updateUiState(UiState.Failure(it)) },
                    onException = { updateUiState(UiState.Failure(it.localizedMessage)) }
                )
            )
        }
    }

    fun getWeatherData() {
        //TODO get pre-saved data from preferences!
        //for now just use the constants
        viewModelScope.launch {
            updateUiState(UiState.Loading)
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


    private fun updateUiState(uiState: UiState) {
        viewModelScope.launch {
            _uiState.emit(
                uiState
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
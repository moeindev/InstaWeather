package ir.moeindeveloper.instaweather.feature.walkthrough.viewModel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.moeindeveloper.instaweather.core.extension.transformToFlow
import ir.moeindeveloper.instaweather.core.platform.viewModel.BaseViewModel
import ir.moeindeveloper.instaweather.core.state.UiState
import ir.moeindeveloper.instaweather.feature.common.entity.IpLocation
import ir.moeindeveloper.instaweather.feature.common.preferences.Settings
import ir.moeindeveloper.instaweather.feature.common.repository.IpLocationRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalkThroughViewModel @Inject constructor(private val repository: IpLocationRepository, val settings: Settings): BaseViewModel() {

    private val locationLoadingChannel: Channel<String> = Channel()

    val ipLocation: StateFlow<UiState<IpLocation>?> = scope {
        locationLoadingChannel.transformToFlow {
            emitAll(
                repository.getLocation().convertToUiState()
            )
        }
    }

    fun loadIpLocation() {
        viewModelScope.launch {
            locationLoadingChannel.send("LoadUp")
        }
    }


    fun saveLocation(location: Settings.Location) {
        settings.location = location
    }

    fun finishUpWalkThough() {
        settings.firstTime = false
        settings.walkThrough = true
    }
}
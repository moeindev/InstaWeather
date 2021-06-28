package ir.moeindeveloper.instaweather.core.platform.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.moeindeveloper.instaweather.core.state.BackgroundState
import ir.moeindeveloper.instaweather.core.state.BackgroundStatus
import ir.moeindeveloper.instaweather.core.state.UiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    inline fun <T> scope(crossinline block: () -> Flow<T>): StateFlow<T?> {
        return MutableStateFlow<T?>(null).apply {
            viewModelScope.launch {
                emitAll(block())
            }
        }
    }

    fun <T> Flow<BackgroundState<T>>.convertToUiState(): Flow<UiState<T>> = map { value ->
        when(value.status) {
            BackgroundStatus.LOADING ->  UiState.loading()
            BackgroundStatus.SUCCESS -> UiState.success(value.data)
            BackgroundStatus.ERROR ->  UiState.failure(value.errorMessage)
            BackgroundStatus.EXCEPTION -> UiState.failure(value.errorMessage)
        }
    }
}
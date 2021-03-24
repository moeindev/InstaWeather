package ir.moeindeveloper.instaweather.core.state

sealed class UiState {
    object Loading : UiState()
    data class Failure(val message: String? = null) : UiState()
    object Success : UiState()
}
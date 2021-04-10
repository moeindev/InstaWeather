package ir.moeindeveloper.instaweather.ui.provider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import ir.moeindeveloper.instaweather.core.state.UiState

class FakeUiStateProvider(override val values: Sequence<UiState>) :
    PreviewParameterProvider<UiState>
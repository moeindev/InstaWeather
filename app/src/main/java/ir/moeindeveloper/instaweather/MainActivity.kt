package ir.moeindeveloper.instaweather

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import dagger.hilt.android.AndroidEntryPoint
import ir.moeindeveloper.instaweather.ui.navigation.SetupNavigation
import ir.moeindeveloper.instaweather.ui.theme.InstaWeatherTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstaWeatherTheme {
                SetupNavigation()
            }
        }
    }
}
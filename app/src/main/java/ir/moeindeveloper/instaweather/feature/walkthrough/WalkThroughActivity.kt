package ir.moeindeveloper.instaweather.feature.walkthrough

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import dagger.hilt.android.AndroidEntryPoint
import ir.moeindeveloper.instaweather.feature.walkthrough.ui.SetupWalkThroughNavigation
import ir.moeindeveloper.instaweather.ui.theme.InstaWeatherTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi
@AndroidEntryPoint
class WalkThroughActivity : AppCompatActivity() {

    @ExperimentalCoroutinesApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstaWeatherTheme {
                SetupWalkThroughNavigation {
                    finish()
                }
            }
        }
    }
}
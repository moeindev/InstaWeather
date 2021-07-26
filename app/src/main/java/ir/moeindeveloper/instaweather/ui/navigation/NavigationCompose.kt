package ir.moeindeveloper.instaweather.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ir.moeindeveloper.instaweather.feature.common.viewModel.WeatherViewModel
import ir.moeindeveloper.instaweather.feature.home.ui.HomeScreen
import ir.moeindeveloper.instaweather.feature.home.ui.homeDestName
import ir.moeindeveloper.instaweather.feature.walkthrough.settings.Settings
import ir.moeindeveloper.instaweather.feature.walkthrough.settings.settingsDestName
import ir.moeindeveloper.instaweather.feature.walkthrough.viewModel.WalkThroughViewModel

@ExperimentalMaterialApi
@Composable
fun SetupNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = homeDestName) {
        composable(homeDestName) {
            val weatherViewModel: WeatherViewModel = hiltViewModel()
            HomeScreen(mainViewModel = weatherViewModel,navController = navController)
        }

        composable(settingsDestName) {
            val walkThroughViewModel: WalkThroughViewModel = hiltViewModel()
            Settings(viewModel = walkThroughViewModel)
        }
    }
}
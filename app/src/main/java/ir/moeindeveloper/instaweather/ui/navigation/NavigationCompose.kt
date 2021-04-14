package ir.moeindeveloper.instaweather.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ir.moeindeveloper.instaweather.feature.common.viewModel.WeatherViewModel
import ir.moeindeveloper.instaweather.feature.home.ui.HomeScreen
import ir.moeindeveloper.instaweather.feature.home.ui.homeDestName

@Composable
fun SetupNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = homeDestName) {
        composable(homeDestName) {
            val weatherViewModel: WeatherViewModel = hiltNavGraphViewModel()
            HomeScreen(mainViewModel = weatherViewModel,navController = navController)
        }

        //TODO add other screens here
    }
}
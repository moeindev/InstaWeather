package ir.moeindeveloper.instaweather.feature.walkthrough.ui

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ir.moeindeveloper.instaweather.feature.walkthrough.viewModel.WalkThroughViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun SetupWalkThroughNavigation() {
    val navController = rememberNavController()
    val viewModel: WalkThroughViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = languageDestName) {

        composable(languageDestName) {
            SelectLanguage(settings = viewModel.settings, navController = navController)
        }

        composable(findLocationNavDest) {
            FindLocation(viewModel = viewModel)
        }
    }
}
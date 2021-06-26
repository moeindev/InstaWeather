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
fun SetupWalkThroughNavigation(onActivityStart: () -> Unit) {
    val navController = rememberNavController()
    val viewModel: WalkThroughViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = splashDestName) {

        composable(splashDestName) {
            Splash(settings = viewModel.settings, navController) {
                onActivityStart()
            }
        }

        composable(languageDestName) {
            SelectLanguage(settings = viewModel.settings, navController = navController) {
                onActivityStart()
            }
        }

        composable(findLocationNavDest) {
            FindLocation(viewModel = viewModel) {
                onActivityStart()
            }
        }
    }
}
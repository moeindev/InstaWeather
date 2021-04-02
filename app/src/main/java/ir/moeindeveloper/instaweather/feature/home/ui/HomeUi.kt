package ir.moeindeveloper.instaweather.feature.home.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

const val homeDestName: String = "home_screen"

@Composable
fun HomeScreen(navController: NavController) {
    Text(text = "WELCOME TO HOME SCREEN")
}
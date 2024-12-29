package com.rolandoselvera.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rolandoselvera.data.remote.models.responses.WeatherResponse
import com.rolandoselvera.ui.screens.DetailScreen
import com.rolandoselvera.ui.screens.HomeScreen
import com.rolandoselvera.ui.screens.SplashScreen
import com.rolandoselvera.utils.fromEncodedJson
import com.rolandoselvera.utils.toEncodedJson

@Composable
fun NavManager() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.SplashScreen.route) {
        composable(Routes.SplashScreen.route) {
            SplashScreen(
                navigateToHome = { navController.navigate(Routes.HomeScreen.route) }
            )
        }

        composable(Routes.HomeScreen.route) {
            HomeScreen(
                navigateToDetail = { weather ->
                    val encodedJson = weather.toEncodedJson()
                    navController.navigate("${Routes.DetailScreen.route}/$encodedJson")
                }
            )
        }

        composable(
            route = "${Routes.DetailScreen.route}/{weatherJson}",
            arguments = listOf(navArgument("weatherJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val weatherJson = backStackEntry.arguments?.getString("weatherJson") ?: ""
            val weather: WeatherResponse = weatherJson.fromEncodedJson()
            DetailScreen(navController, weather)
        }
    }
}
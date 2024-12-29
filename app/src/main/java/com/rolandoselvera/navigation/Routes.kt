package com.rolandoselvera.navigation

sealed class Routes(
    val title: String,
    val route: String,
) {
    data object SplashScreen : Routes("SplashScreen", "splash_screen")
    data object HomeScreen : Routes("HomeScreen", "home_screen")
    data object DetailScreen : Routes("DetailScreen", "detail_screen")
}
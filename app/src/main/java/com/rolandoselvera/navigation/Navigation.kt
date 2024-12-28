package com.rolandoselvera.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rolandoselvera.ui.screens.HomeScreen
import com.rolandoselvera.ui.screens.SplashScreen

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(
                navigateToHome = { navController.navigate("home_screen") }
            )
        }

        composable("home_screen") {
            HomeScreen()
        }
    }
}

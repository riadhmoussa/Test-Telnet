package com.moussa.telnettest.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.moussa.telnettest.presentation.town_fetaures.add.AddTownScreen
import com.moussa.telnettest.presentation.town_fetaures.details.TownWeatherDetailsScreen
import com.moussa.telnettest.presentation.town_fetaures.home.HomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.AddTown.route) {
            AddTownScreen(navController = navController)
        }
        composable(
            route = Screen.TownWeatherDetails.route +
                    "?name={name}",
            arguments = listOf(
                navArgument(
                    name = "name"
                ) {
                    type = NavType.StringType
                    defaultValue = ""
                },
            )
        ) {
            TownWeatherDetailsScreen()
        }


    }
}
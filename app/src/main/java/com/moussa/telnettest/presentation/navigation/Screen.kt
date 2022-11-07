package com.moussa.telnettest.presentation.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object AddTown : Screen("add_town_screen")
    object TownWeatherDetails : Screen("town_weather_details_screen")
}

package com.capstoneC23PS274.segar.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Dictionary : Screen("dictionary")
    object Check : Screen("check")
    object History : Screen("history")
    object Profile : Screen("profile")
}
package com.capstoneC23PS274.segar.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Dictionary : Screen("dictionary")
    object DictionaryDetail : Screen("dictionary/{dictId}"){
        fun createRoute(dictId: String) = "dictionary/$dictId"
    }
    object Check : Screen("check")
    object Result : Screen("check/{resultId}"){
        fun createRoute(resultId: String) = "check/$resultId"
    }
    object History : Screen("history")
    object Profile : Screen("profile")
    object Faq : Screen("faq")
    object Login : Screen("login")
    object Register : Screen("register")
    object Splash : Screen("splash")
    object InternetConnection : Screen("internet")
}
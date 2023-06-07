package com.capstoneC23PS274.segar

import android.app.Application
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.capstoneC23PS274.segar.ui.component.BottomBar
import com.capstoneC23PS274.segar.ui.component.CameraFAB
import com.capstoneC23PS274.segar.ui.navigation.Screen
import com.capstoneC23PS274.segar.ui.screen.camera.CameraScreen
import com.capstoneC23PS274.segar.ui.screen.dictdetail.DictDetailScreen
import com.capstoneC23PS274.segar.ui.screen.dictionary.DictionaryScreen
import com.capstoneC23PS274.segar.ui.screen.faq.FAQScreen
import com.capstoneC23PS274.segar.ui.screen.history.HistoryScreen
import com.capstoneC23PS274.segar.ui.screen.home.HomeScreen
import com.capstoneC23PS274.segar.ui.screen.login.LoginScreen
import com.capstoneC23PS274.segar.ui.screen.profile.ProfileScreen
import com.capstoneC23PS274.segar.ui.screen.register.RegisterScreen
import com.capstoneC23PS274.segar.ui.screen.result.ResultScreen
import com.capstoneC23PS274.segar.ui.screen.splash.SplashScreen
import com.capstoneC23PS274.segar.ui.theme.SegarTheme

@Composable
fun SegarApp(
    application: Application,
    modifier : Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val noBottomNav = listOf<String>(
        Screen.Login.route,
        Screen.Register.route,
        Screen.Check.route,
        Screen.Splash.route
    )
    Scaffold(
        bottomBar = {
            if (currentRoute !in noBottomNav){
                BottomBar(navController = navController)
            }
        },
        floatingActionButton = {
            if (currentRoute !in noBottomNav){
                CameraFAB(onClick = {
                    navController.navigate(Screen.Check.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                })
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        modifier = modifier,
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.Dictionary.route) {
                DictionaryScreen(itemOnClick = { dictId ->
                    navController.navigate(Screen.DictionaryDetail.createRoute(dictId))
                })
            }
            composable(
                route = Screen.DictionaryDetail.route,
                arguments = listOf(navArgument("dictId"){
                    type = NavType.StringType
                })
            ) {
                val dictId = it.arguments?.getString("dictId") ?: ""
                DictDetailScreen(dictItemId = dictId)
            }
            composable(Screen.Check.route) {
                CameraScreen(
                    application = application,
                    toResult = { resultId ->
                        navController.navigate(Screen.Result.createRoute(resultId)){
                            popUpTo(navController.graph.id) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
            composable(
                route = Screen.Result.route,
                arguments = listOf(navArgument("resultId"){
                    type = NavType.StringType
                })
            ) {
                val resultId = it.arguments?.getString("resultId") ?: ""
                ResultScreen(checkId = resultId)
            }
            composable(Screen.History.route) {
                HistoryScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen(toFaq = {
                    navController.navigate(Screen.Faq.route){
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }, logout = {
                    navController.navigate(Screen.Login.route){
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                })
            }
            composable(Screen.Faq.route){
                FAQScreen()
            }
            composable(Screen.Login.route){
                LoginScreen(goToMain = {
                    navController.navigate(Screen.Home.route){
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }, goToRegister = {
                    navController.navigate(Screen.Register.route)
                })
            }
            composable(Screen.Register.route){
                RegisterScreen(goToLogin = {
                    navController.navigate(Screen.Login.route){
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                })
            }
            composable(Screen.Splash.route){
                SplashScreen(toLogin = {
                    navController.navigate(Screen.Login.route){
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                }, toHome = {
                    navController.navigate(Screen.Home.route){
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SegarAppPreview() {
    SegarTheme {
//        SegarApp()
    }
}
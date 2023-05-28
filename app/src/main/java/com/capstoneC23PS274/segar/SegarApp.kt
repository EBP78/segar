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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.capstoneC23PS274.segar.ui.component.BottomBar
import com.capstoneC23PS274.segar.ui.component.CameraFAB
import com.capstoneC23PS274.segar.ui.navigation.Screen
import com.capstoneC23PS274.segar.ui.screen.camera.CameraScreen
import com.capstoneC23PS274.segar.ui.theme.SegarTheme

@Composable
fun SegarApp(
    application: Application,
    modifier : Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.Check.route){
                BottomBar(navController = navController)
            }
        },
        floatingActionButton = {
            if (currentRoute != Screen.Check.route){
                FloatingActionButton(
                    onClick = {
                        navController.navigate(Screen.Check.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    },
                    backgroundColor = MaterialTheme.colors.primary,
                    modifier = modifier
                        .testTag("Check")
                        .size(75.dp)
                        .border(BorderStroke(7.dp, Color.White), CircleShape)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_camera_alt_24),
                        contentDescription = stringResource(R.string.menu_check),
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        modifier = modifier,
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {

            }
            composable(Screen.Dictionary.route) {

            }
            composable(Screen.Check.route) {
                CameraScreen(
                    application = application,
                    toResult = {
                        // temporary
                        // please change
                        navController.navigate(Screen.Home.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable(Screen.History.route) {

            }
            composable(Screen.Profile.route) {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SegarAppPreview() {
    SegarTheme() {
//        SegarApp()
    }
}
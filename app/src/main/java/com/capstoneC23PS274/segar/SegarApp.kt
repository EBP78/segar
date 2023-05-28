package com.capstoneC23PS274.segar

import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.capstoneC23PS274.segar.ui.component.BottomBar
import com.capstoneC23PS274.segar.ui.component.CameraFAB
import com.capstoneC23PS274.segar.ui.navigation.Screen
import com.capstoneC23PS274.segar.ui.theme.SegarTheme

@Composable
fun SegarApp(
    modifier : Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        },
        floatingActionButton = {
            CameraFAB(onClick = {

            })
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
        SegarApp()
    }
}
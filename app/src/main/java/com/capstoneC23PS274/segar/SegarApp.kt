package com.capstoneC23PS274.segar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.capstoneC23PS274.segar.ui.component.BottomBar
import com.capstoneC23PS274.segar.ui.navigation.Screen
import com.capstoneC23PS274.segar.ui.theme.SegarTheme
import com.capstoneC23PS274.segar.ui.theme.Shapes

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
            FloatingActionButton(
                onClick = { },
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
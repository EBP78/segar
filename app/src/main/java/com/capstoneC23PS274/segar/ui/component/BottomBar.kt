package com.capstoneC23PS274.segar.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.capstoneC23PS274.segar.R
import com.capstoneC23PS274.segar.ui.navigation.NavigationItem
import com.capstoneC23PS274.segar.ui.navigation.Screen
import com.capstoneC23PS274.segar.ui.theme.MainGreen
import com.capstoneC23PS274.segar.ui.theme.SegarTheme

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        modifier = modifier.clip(RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp))
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home,
                contentDescription = stringResource(R.string.menu_home)
            ),
            NavigationItem(
                title = stringResource(R.string.menu_dict),
                icon = Icons.Default.Info,
                screen = Screen.Dictionary,
                contentDescription = stringResource(R.string.menu_dict)
            ),
            NavigationItem(
                title = "",
                icon = null,
                screen = null,
                contentDescription = ""
            ),
            NavigationItem(
                title = stringResource(R.string.menu_history),
                icon = Icons.Default.Create,
                screen = Screen.History,
                contentDescription = stringResource(R.string.menu_history)
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile,
                contentDescription = stringResource(R.string.menu_profile)
            ),
        )
        BottomNavigation {
            navigationItems.map { item ->
                BottomNavigationItem(
                    icon = {
                        if (item.icon != null){
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.contentDescription
                            )
                        }
                    },
                    label = { Text(item.title) },
                    selected = currentRoute == item.screen?.route,
                    onClick = {
                        if (item.screen != null){
                            navController.navigate(item.screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                restoreState = true
                                launchSingleTop = true
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun CameraFAB(
    onClick : () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = onClick,
        backgroundColor = MaterialTheme.colors.primary,
        modifier = modifier
            .testTag(stringResource(id = R.string.cameraFAB))
            .size(75.dp)
            .border(BorderStroke(7.dp, Color.White), CircleShape)
    ) {
        Icon(
            painter = painterResource(R.drawable.baseline_camera_alt_24),
            contentDescription = stringResource(R.string.menu_check),
        )
    }
}

@Composable
@Preview(showBackground = true)
fun BottomBarPreview() {
    SegarTheme {
        BottomBar(navController = rememberNavController())
    }
}

@Composable
@Preview(showBackground = true)
fun CameraFABPreview() {
    SegarTheme {
        CameraFAB(onClick = {

        })
    }
}
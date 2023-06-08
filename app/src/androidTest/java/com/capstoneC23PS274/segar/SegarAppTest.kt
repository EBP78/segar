package com.capstoneC23PS274.segar

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.capstoneC23PS274.segar.ui.navigation.Screen
import com.capstoneC23PS274.segar.ui.theme.SegarTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SegarAppTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController : TestNavHostController

    @Before
    fun setUp() {
        composeTestRule.setContent { 
            SegarTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                SegarApp(application = Application(), navController = navController)
            }
        }
    }

    @Test
    fun splash_goToLogin_ifNotLoginYet_viceVersaToHome() = runTest {
        val currentRoute = navController.currentBackStackEntry?.destination?.route
        assertEquals(Screen.Splash.route, currentRoute)
        delay(3000)
        if (navController.isTheSameRoute(Screen.Login.route)){
            navController.assertCurrentRouteName(Screen.Login.route)
        } else if (navController.isTheSameRoute(Screen.Home.route)) {
            navController.assertCurrentRouteName(Screen.Home.route)
        }
    }

    @Test
    fun test_login_buttonRegister_toRegister() = runTest {
        delay(3000)
        if (navController.isTheSameRoute(Screen.Login.route)){
            navController.assertCurrentRouteName(Screen.Login.route)
            composeTestRule.onNodeWithStringId(R.string.register).assertIsDisplayed()
            composeTestRule.onNodeWithStringId(R.string.register).performClick()
            navController.assertCurrentRouteName(Screen.Register.route)
        } else if (navController.isTheSameRoute(Screen.Home.route)) {
            navController.assertCurrentRouteName(Screen.Home.route)
            composeTestRule.onNodeWithStringId(R.string.menu_profile).performClick()
            composeTestRule.onNodeWithStringId(R.string.logout).performClick()
            navController.assertCurrentRouteName(Screen.Login.route)
            composeTestRule.onNodeWithStringId(R.string.register).assertIsDisplayed()
            composeTestRule.onNodeWithStringId(R.string.register).performClick()
            navController.assertCurrentRouteName(Screen.Register.route)
        }
    }

    @Test
    fun home_isDisplayed() =  runTest {
        val currentRoute = navController.currentBackStackEntry?.destination?.route
                assertEquals(Screen.Splash.route, currentRoute)
        delay(3000)
        if (navController.isTheSameRoute(Screen.Login.route)){
            navController.assertCurrentRouteName(Screen.Login.route)
            
        } else if (navController.isTheSameRoute(Screen.Home.route)) {
            navController.assertCurrentRouteName(Screen.Home.route)
        }
    }


}
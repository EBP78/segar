package com.capstoneC23PS274.segar

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.capstoneC23PS274.segar.ui.navigation.Screen
import com.capstoneC23PS274.segar.ui.theme.SegarTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.advanceUntilIdle
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
        navController.assertCurrentRouteName(Screen.Splash.route)
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
        navController.assertCurrentRouteName(Screen.Splash.route)
        composeTestRule.mainClock.advanceTimeBy(4000)
        if (navController.isTheSameRoute(Screen.Login.route)){
            navController.assertCurrentRouteName(Screen.Login.route)
            composeTestRule.onNodeWithTagStringId(R.string.email).performTextInput("testaj4@gmail.com")
            composeTestRule.onNodeWithTagStringId(R.string.password).performTextInput("asd")
            composeTestRule.onNodeWithStringId(R.string.login).performClick()
            advanceUntilIdle()
            delay(3000)
        }
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun all_bottomNavigationWorks() = runTest {
        home_isDisplayed()
        composeTestRule.onNodeWithStringId(R.string.menu_dict).performClick()
        navController.assertCurrentRouteName(Screen.Dictionary.route)
        composeTestRule.onNodeWithStringId(R.string.menu_history).performClick()
        navController.assertCurrentRouteName(Screen.History.route)
        composeTestRule.onNodeWithStringId(R.string.menu_profile).performClick()
        navController.assertCurrentRouteName(Screen.Profile.route)
        composeTestRule.onNodeWithStringId(R.string.menu_home).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun home_contentIsDisplayed() = runTest {
        home_isDisplayed()
        composeTestRule.onNodeWithContentDescStringId(R.string.app_logo).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescStringId(R.string.splash_image).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescStringId(R.string.tagline).assertIsDisplayed()
    }


}
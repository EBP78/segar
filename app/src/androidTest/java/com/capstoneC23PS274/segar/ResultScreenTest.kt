package com.capstoneC23PS274.segar

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.testing.TestNavHostController
import com.capstoneC23PS274.segar.ui.screen.result.ResultScreen
import com.capstoneC23PS274.segar.ui.theme.SegarTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class, ExperimentalTestApi::class)
class ResultScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController : TestNavHostController

    @Test
    fun result_idDisplayedProperly() = runTest {
//        home_isDisplayed()
        composeTestRule.setContent {
            SegarTheme {
                ResultScreen(checkId = "6479f0a378f1c720956f435e")
            }
        }

        composeTestRule.waitUntilDoesNotExist(hasText("Loading..."), 5000)

        composeTestRule.onNodeWithContentDescStringId(R.string.vegetable_picture).assertIsDisplayed()
        composeTestRule.onNodeWithText("Kol").assertIsDisplayed()
        composeTestRule.onNodeWithText("85").assertIsDisplayed()
        composeTestRule.onNodeWithText("Fri, Jun 02, 2023").assertIsDisplayed()
    }
}
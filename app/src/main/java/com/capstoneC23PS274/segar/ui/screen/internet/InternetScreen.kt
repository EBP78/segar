package com.capstoneC23PS274.segar.ui.screen.internet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import com.capstoneC23PS274.segar.ui.component.ErrorModal
import kotlinx.coroutines.delay

@Composable
fun InternetScreen(
    message: String,
    modifier: Modifier = Modifier
) {
    val isVisible = remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = true){
        delay(1000)
        isVisible.value = true
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black.copy(alpha = 0.6f)
    ) {
        AnimatedVisibility(
            visible = isVisible.value,
            enter = fadeIn(animationSpec = tween(1000, easing = LinearEasing)),
            exit = fadeOut(animationSpec = tween(1000, easing = LinearEasing))
        ) {
            Box(modifier = modifier.fillMaxSize()){
                ErrorModal(
                    message = message,
                    isDisplayed = true,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}
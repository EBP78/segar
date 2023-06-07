package com.capstoneC23PS274.segar.ui.screen.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.capstoneC23PS274.segar.R
import com.capstoneC23PS274.segar.di.Injection
import com.capstoneC23PS274.segar.utils.ViewModelFactory
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    toLogin: () -> Unit,
    toHome: () -> Unit,
    modifier: Modifier = Modifier,
    viewmodel: SplashViewmodel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    )
) {
    LaunchedEffect(key1 = true){
        viewmodel.changeVisible()
        delay(1500)
        val isLogin = viewmodel.checkIsLogin()
        viewmodel.changeVisible()
        delay(1000)
        if (isLogin){
            toHome()
        } else {
            toLogin()
        }
    }
    val isVisible by viewmodel.logoVisible
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(animationSpec = tween(1000, easing = LinearEasing)),
        exit = fadeOut(animationSpec = tween(1000, easing = LinearEasing))
    ) {
        Box(modifier = modifier.fillMaxSize()) {
            Image(
                painterResource(R.drawable.logo_capstone_long),
                contentDescription = stringResource(id = R.string.app_logo),
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun RegisterPreview() {
    MaterialTheme {
       SplashScreen(toLogin = {

       }, toHome = {

       })
    }
}
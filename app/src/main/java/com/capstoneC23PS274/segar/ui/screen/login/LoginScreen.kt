package com.capstoneC23PS274.segar.ui.screen.login

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.capstoneC23PS274.segar.R
import com.capstoneC23PS274.segar.di.Injection
import com.capstoneC23PS274.segar.ui.common.UiState
import com.capstoneC23PS274.segar.ui.component.ErrorModal
import com.capstoneC23PS274.segar.ui.component.FormInput
import com.capstoneC23PS274.segar.ui.component.LoadingAnimation
import com.capstoneC23PS274.segar.ui.theme.MainGreen
import com.capstoneC23PS274.segar.utils.ViewModelFactory

@Composable
fun LoginScreen(
    goToMain: () -> Unit,
    goToRegister: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewmodel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    context : Context = LocalContext.current
) {
    val email by viewModel.email
    val password by viewModel.password
    val loading by viewModel.loading
    val errShow by viewModel.errorShow
    val errMess by viewModel.errorMessage
    Box (
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Image(
                painterResource(R.drawable.logo_capstone),
                contentDescription = stringResource(id = R.string.app_logo),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(250.dp)
                    .height(250.dp)
                    .padding(50.dp)
            )

            FormInput(
                query = email,
                onQueryChange = { newString ->
                    viewModel.updateEmail(newString)
                },
                placeholder = stringResource(id = R.string.email)
            )
            FormInput(
                query = password,
                onQueryChange = {newString ->
                    viewModel.updatePassword(newString)
                },
                placeholder = stringResource(id = R.string.password),
                isPassword = true
            )
            viewModel.loginResult.collectAsState(initial = UiState.Loading).value.let { uiState ->
                when(uiState) {
                    is UiState.Loading -> {
                        Button(
                            onClick = {
                                viewModel.login()
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = MainGreen),
                            modifier = Modifier
                                .widthIn(min = 200.dp)
                                .padding(10.dp),
                        ) {
                            Text(
                                text = stringResource(id = R.string.login),
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 25.sp,
                            )
                        }
                    }
                    is UiState.Success -> {
                        if (uiState.data.data != null){
                            goToMain()
                            viewModel.isFinished()
                        } else {
                            viewModel.showError(uiState.data.message.toString())
                            viewModel.isFinished()
                        }
                    }
                    is UiState.Error -> {
                        viewModel.showError(uiState.errorMessage)
                    }
                }
            }
        }
        Button(
            onClick = goToRegister,
            colors = ButtonDefaults.buttonColors(backgroundColor = MainGreen),
            modifier = Modifier
                .widthIn(min = 150.dp)
                .padding(10.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = stringResource(id = R.string.register),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )
        }
        LoadingAnimation(
            isDisplayed = loading,
            modifier = Modifier.align(Alignment.Center)
        )
        ErrorModal(
            message = errMess,
            isDisplayed = errShow,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


@Composable
@Preview(showBackground = true)
fun LoginPreview() {
    MaterialTheme {
        LoginScreen(goToMain = {

        }, goToRegister = {

        })
    }
}
package com.capstoneC23PS274.segar.ui.screen.login

import android.content.Context
import android.widget.Toast
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.capstoneC23PS274.segar.di.Injection
import com.capstoneC23PS274.segar.ui.component.FormInput
import com.capstoneC23PS274.segar.ui.theme.MainGreen
import com.capstoneC23PS274.segar.utils.ViewModelFactory
import androidx.compose.runtime.getValue
import com.capstoneC23PS274.segar.ui.common.UiState
import com.capstoneC23PS274.segar.ui.component.ErrorModal
import com.capstoneC23PS274.segar.ui.component.LoadingAnimation

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
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://cdn.pixabay.com/photo/2016/07/16/03/50/pigs-1520968_1280.jpg")
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
                    .padding(50.dp)
            )

            FormInput(
                query = email,
                onQueryChange = { newString ->
                    viewModel.updateEmail(newString)
                },
                placeholder = "Email"
            )
            FormInput(
                query = password,
                onQueryChange = {newString ->
                    viewModel.updatePassword(newString)
                },
                placeholder = "Password",
                isPassword = true
            )
            viewModel.loginResult.collectAsState(initial = UiState.Loading).value.let { uiState ->
                when(uiState) {
                    is UiState.Loading -> {
                        Button(
                            onClick = {
                                // viewmodel funciton here
                                // success go to main
                                viewModel.login()
//                                Toast.makeText(context,"berhasil", Toast.LENGTH_SHORT).show()
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = MainGreen),
                            modifier = Modifier
                                .widthIn(min = 200.dp)
                                .padding(10.dp),
                        ) {
                            Text(
                                text = "Login",
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
                        Toast.makeText(context,"gagal", Toast.LENGTH_SHORT).show()
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
                text = "Register",
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
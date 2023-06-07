package com.capstoneC23PS274.segar.ui.screen.register

import android.content.Context
import android.widget.Toast
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
fun RegisterScreen(
    goToLogin: () -> Unit,
    modifier: Modifier = Modifier,
    viewmodel: RegisterViewmodel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    context: Context = LocalContext.current,
) {
    val username by viewmodel.username
    val email by viewmodel.email
    val password by viewmodel.password
    val confirmPassword by viewmodel.confirmPassword
    val canClick by viewmodel.canClick
    val loading by viewmodel.loading
    val errShow by viewmodel.errorShow
    val errMess by viewmodel.errorMessage
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
                contentDescription = "app logo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(250.dp)
                    .height(250.dp)
                    .padding(50.dp)
            )
            FormInput(
                query = username,
                onQueryChange = { viewmodel.updateUsername(it) },
                placeholder = "Username"
            )
            FormInput(query = email,
                onQueryChange = { viewmodel.updateEmail(it) },
                placeholder = "Email"
            )
            FormInput(query = password,
                onQueryChange = { viewmodel.updatePassword(it) },
                placeholder = "Password",
                isPassword = true
            )
            FormInput(query = confirmPassword,
                onQueryChange = { viewmodel.updateConfirmPassword(it) },
                placeholder = "Confirm Password",
                isPassword = true
            )
            viewmodel.registerResult.collectAsState(initial = UiState.Loading).value.let { uiState ->
                when(uiState) {
                    is UiState.Loading -> {
                        Button(
                            modifier = Modifier
                                .widthIn(min = 200.dp)
                                .padding(10.dp),
                            enabled = canClick,
                            onClick = { viewmodel.registerUser() },
                            colors = ButtonDefaults.buttonColors(backgroundColor = MainGreen)
                        ) {
                            Text(
                                text = "Register",
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 25.sp,
                            )
                        }
                    }
                    is UiState.Success -> {
                        goToLogin()
                        viewmodel.isFinished()
                    }
                    is UiState.Error -> {
                        viewmodel.showError(uiState.errorMessage)
                    }
                }
            }
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
    fun RegisterPreview() {
        MaterialTheme {
            RegisterScreen(
                goToLogin = {
                            
                },
                modifier = Modifier
            )
        }
    }
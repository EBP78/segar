package com.capstoneC23PS274.segar.ui.screen.register

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
import com.capstoneC23PS274.segar.ui.component.LoadingAnimation

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
                        Toast.makeText(context, "gagal", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        LoadingAnimation(
            isDisplayed = loading,
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
package com.capstoneC23PS274.segar.ui.screen.login

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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.capstoneC23PS274.segar.ui.component.FormInput
import com.capstoneC23PS274.segar.ui.theme.MainGreen

@Composable
fun LoginScreen(
    goToMain: () -> Unit,
    goToRegister: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box {
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

            FormInput(query = "", onQueryChange = {}, placeholder = "Email")
            FormInput(query = "", onQueryChange = {}, placeholder = "Password")
            Button(
                onClick = {
                          // viewmodel funciton here
                          // success go to main
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
package com.capstoneC23PS274.segar.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.capstoneC23PS274.segar.R


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://cdn.pixabay.com/photo/2016/07/16/03/50/pigs-1520968_1280.jpg")
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(250.dp)
                .height(250.dp)
                .padding(50.dp)
                .align(Alignment.TopCenter)
        )
        Image(
            painterResource(R.drawable.green_home),
            contentDescription = "splash image",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
        Image(
            painterResource(R.drawable.tagline),
            contentDescription = "splash tagline",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .width(400.dp)
                .height(300.dp)
                .align(Alignment.Center)
        )
    }
}


@Composable
@Preview(showBackground = true)
fun HomePreview() {
    MaterialTheme {
        HomeScreen()
    }
}
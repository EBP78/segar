package com.capstoneC23PS274.segar.ui.screen.result

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.capstoneC23PS274.segar.R
import com.capstoneC23PS274.segar.ui.theme.MainGreen
import com.capstoneC23PS274.segar.ui.theme.Typography

@Composable
fun ResultScreen(
    checkId: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier.fillMaxSize()
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("url from api, viewmodel")
                .crossfade(true)
                .build(),
            contentDescription = "gambar sayur",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(225.dp)
                .height(350.dp)
                .padding(8.dp)
                .border(BorderStroke(3.dp, MainGreen))
        )
        Icon(
            painter = painterResource(id = R.drawable.baseline_check_circle_24),
            contentDescription = "Expandable Arrow",
            tint = MainGreen,
            modifier = Modifier
                .width(75.dp)
                .height(75.dp)
        )
        Text(
            text = "vegetable name, from api",
            style = Typography.h5,
            fontWeight = FontWeight.Bold,
            color = MainGreen
        )
        Text(
            text = "persentage, from api",
            style = Typography.h6,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Date, from api",
            style = Typography.subtitle1,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ResultScreenPreview() {
    MaterialTheme {
        ResultScreen(checkId = "001")
    }
}
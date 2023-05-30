package com.capstoneC23PS274.segar.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.capstoneC23PS274.segar.ui.theme.MainGreen
import com.capstoneC23PS274.segar.ui.theme.Typography

@Composable
fun DetailTextItem (
    title: String,
    content: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = Typography.h6,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 10.dp)
    )
    Text(
        text = content,
        style = Typography.subtitle1,
        modifier = Modifier.padding(bottom = 15.dp, start = 10.dp)
    )
}

@Composable
fun ImageCarousell (
    urlList: List<String>,
    modifier: Modifier = Modifier
) {
    LazyRow {
        items(urlList){ imgUrl ->
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imgUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Vegetable Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillParentMaxWidth()
                    .height(250.dp)
                    .padding(8.dp)
                    .border(BorderStroke(3.dp, MainGreen))
            )
        }
    }
}

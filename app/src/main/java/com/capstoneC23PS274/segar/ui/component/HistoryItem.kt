package com.capstoneC23PS274.segar.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun HistoryList(
    modifier: Modifier = Modifier,
) {
    Card (
        modifier = modifier.fillMaxWidth(),
        border = BorderStroke(5.dp, Color.Green)
    ){
        Row (){
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://cdn.pixabay.com/photo/2016/07/16/03/50/pigs-1520968_1280.jpg")
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)

            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = "Sawi",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 30.sp
                )


                Text(
                    text = "70% segar",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 30.sp,
                )


                Text(
                    text = "12 Januari 2025",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                )
            }
        }

        }
    }


@Composable
@Preview(showBackground = true)
fun HistoryPreview() {
    MaterialTheme {
        HistoryList(
            modifier = Modifier
        )
    }
}
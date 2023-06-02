package com.capstoneC23PS274.segar.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.capstoneC23PS274.segar.R
import com.capstoneC23PS274.segar.data.remote.response.DictionaryItem
import com.capstoneC23PS274.segar.ui.theme.MainGreen

@Composable
fun DictionaryItem (
    itemData: DictionaryItem,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        border = BorderStroke(3.dp, MainGreen)
    ) {
        Box {
            Row(
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(itemData.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(100.dp)
                        .height(150.dp)
                        .padding(8.dp)
                        .border(BorderStroke(3.dp, MainGreen))
                )
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = itemData.name.toString(),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 30.sp
                    )


                    Text(
                        text = itemData.scientificName.toString(),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )

                    Text(
                        text = itemData.famili.toString(),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )

                    Text(
                        text = itemData.consumablePart.toString(),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
            Icon(
                painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                contentDescription = "Expandable Arrow",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable{
                        onClick(itemData.id.toString())
                    }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DictionaryPreview() {
    MaterialTheme {
        val dictionaryItem = DictionaryItem("001", "sawi", "sawitius desu", "https://cdn.pixabay.com/photo/2016/07/16/03/50/pigs-1520968_1280.jpg", "bebek", "daun")
        DictionaryItem(
            itemData = dictionaryItem,
            modifier = Modifier,
            onClick = { id ->

            }
        )
    }
}
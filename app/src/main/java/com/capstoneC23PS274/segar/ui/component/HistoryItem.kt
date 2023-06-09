package com.capstoneC23PS274.segar.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.capstoneC23PS274.segar.data.remote.response.HistoryItem
import com.capstoneC23PS274.segar.ui.theme.MainGreen
import com.capstoneC23PS274.segar.utils.formatDate
import com.capstoneC23PS274.segar.R

@Composable
fun HistoryListItem(
    itemData: HistoryItem,
    modifier: Modifier = Modifier,
) {
    Card (
        modifier = modifier.fillMaxWidth(),
        border = BorderStroke(3.dp, MainGreen)
    ){
        Row {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(itemData.image)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(id = R.string.vegetable_picture),
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
                    text = itemData.score.toString(),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 30.sp,
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(15.dp)
                )

                Text(
                    text = formatDate(itemData.createdAt.toString()),
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
        val historyItem = HistoryItem( "85.0","https://cdn.pixabay.com/photo/2016/07/16/03/50/pigs-1520968_1280.jpg", "80% Seggar", "orang", 1, "Sawi", "oaisdfoi23")
        HistoryListItem(
            itemData = historyItem,
            modifier = Modifier
        )
    }
}
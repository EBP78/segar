package com.capstoneC23PS274.segar.ui.screen.dictdetail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstoneC23PS274.segar.ui.component.DetailTextItem
import com.capstoneC23PS274.segar.ui.component.ImageCarousell
import com.capstoneC23PS274.segar.ui.theme.MainGreen
import com.capstoneC23PS274.segar.ui.theme.Typography

@Composable
fun DictDetailScreen (
    dictItemId: String,
    modifier: Modifier = Modifier
) {
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            // example of using dictionary detail component
            val dummylist = listOf<String>("https://asset.kompas.com/crops/fIaNWDAjRZ8OzH-6PTSsBisOyA0=/87x0:759x448/750x500/data/photo/2023/03/05/64049a48c2ac7.jpg", "https://www.kampustani.com/wp-content/uploads/2020/11/Cara-Menanam-Sawi-Pakcoy-di-Polybag-Secara-Organik.jpg")
            ImageCarousell(urlList = dummylist)
        }
        item {
            Text(
                text = "name of vegetable",
                style = Typography.h5,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            Text(
                text = "Latin name of the vegetable",
                style = Typography.h6,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            Divider(
                color = MainGreen,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp)
                    .height(5.dp)
            )
        }
        item {
            DetailTextItem(title = "Famili", content = "Lorem Ipsum Dolor Amet")
        }
        item {
            DetailTextItem(title = "Bagian yang dapat dikonsumsi", content = "Lorem Ipsum Dolor Amet")
        }
        item {
            DetailTextItem(title = "Tanah Asal", content = "Lorem Ipsum Dolor Amet")
        }
        item {
            Divider(
                color = MainGreen,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp)
                    .height(5.dp)
            )
        }
        item {
            DetailTextItem(title = "Informasi Lainnya", content = "Lorem Ipsum Dolor Amet")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ResultScreenPreview() {
    MaterialTheme {
        DictDetailScreen(dictItemId = "01")
    }
}
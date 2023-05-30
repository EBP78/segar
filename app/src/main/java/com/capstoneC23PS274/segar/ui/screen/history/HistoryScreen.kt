package com.capstoneC23PS274.segar.ui.screen.history

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.capstoneC23PS274.segar.data.remote.response.HistoryResponseItem
import com.capstoneC23PS274.segar.ui.component.HistoryListItem

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier
) {
    // getting data from viewmodel
    // example of history item use
    val historyItem = HistoryResponseItem("001", "sawi", "https://cdn.pixabay.com/photo/2016/07/16/03/50/pigs-1520968_1280.jpg", "80% Seggar", "10 oktober 2023")
    HistoryListItem(
        itemData = historyItem,
        modifier = Modifier.padding(10.dp)
    )
}
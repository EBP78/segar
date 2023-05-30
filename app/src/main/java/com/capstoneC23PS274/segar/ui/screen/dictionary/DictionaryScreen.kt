package com.capstoneC23PS274.segar.ui.screen.dictionary

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.capstoneC23PS274.segar.data.remote.response.DictionaryItemData
import com.capstoneC23PS274.segar.ui.component.DictionaryItem

@Composable
fun DictionaryScreen (
    itemOnClick : (String) -> Unit,
    modifier : Modifier = Modifier
) {
    // onclick function will navigate to deatil
    // getting data from viewmodel
    // example of item use
    val dictionaryItem = DictionaryItemData("001", "sawi", "sawitius desu", "https://cdn.pixabay.com/photo/2016/07/16/03/50/pigs-1520968_1280.jpg", "bebek", "daun", "none")
    DictionaryItem(
        itemData = dictionaryItem,
        modifier = Modifier,
        onClick = itemOnClick
    )
}
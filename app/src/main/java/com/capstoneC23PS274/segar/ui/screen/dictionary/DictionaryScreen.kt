package com.capstoneC23PS274.segar.ui.screen.dictionary

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.capstoneC23PS274.segar.di.Injection
import com.capstoneC23PS274.segar.ui.common.UiState
import com.capstoneC23PS274.segar.ui.component.DictionaryItem
import com.capstoneC23PS274.segar.utils.ViewModelFactory
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import com.capstoneC23PS274.segar.ui.component.LoadingAnimation

@Composable
fun DictionaryScreen (
    itemOnClick : (String) -> Unit,
    modifier : Modifier = Modifier,
    viewmodel: DictionaryViewmodel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    context: Context = LocalContext.current
) {
    val loading by viewmodel.loading
    Box(modifier = modifier.fillMaxSize()){
        viewmodel.dictionary.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when(uiState) {
                is UiState.Loading -> {
                    viewmodel.getAllDictionary()
                }
                is UiState.Success -> {
                    LazyColumn (
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.padding(10.dp)
                    ){
                        items(uiState.data) { dictionaryItem ->
                            DictionaryItem(
                                itemData = dictionaryItem,
                                onClick = {
                                    itemOnClick(dictionaryItem.id.toString())
                                }
                            )
                        }
                    }
                }
                is UiState.Error -> {
                    Toast.makeText(context, "gagal", Toast.LENGTH_SHORT).show()
                }
            }
        }
        LoadingAnimation(
            isDisplayed = loading,
            modifier = Modifier.align(Alignment.Center)
        )
    }

}
package com.capstoneC23PS274.segar.ui.screen.history

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.capstoneC23PS274.segar.ui.component.HistoryListItem
import com.capstoneC23PS274.segar.ui.component.LoadingAnimation
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.capstoneC23PS274.segar.data.remote.response.HistoryItem
import com.capstoneC23PS274.segar.di.Injection
import com.capstoneC23PS274.segar.ui.common.UiState
import com.capstoneC23PS274.segar.ui.component.ErrorModal
import com.capstoneC23PS274.segar.utils.ViewModelFactory

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    viewmodel: HistoryViewmodel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    context : Context = LocalContext.current
) {
    val loading by viewmodel.loading
    val errShow by viewmodel.errorShow
    val errMess by viewmodel.errorMessage
    Box(modifier = modifier.fillMaxSize()) {
        viewmodel.history.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when(uiState){
                is UiState.Loading -> {
                    viewmodel.getHistory()
                }
                is UiState.Success -> {
                    if (uiState.data.data != null) {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier.padding(10.dp)
                        ) {
                            items(uiState.data.data) { HistoryItem ->
                                HistoryListItem(itemData = HistoryItem)
                            }
                        }
                    } else {
                        viewmodel.showError(uiState.data.message.toString())
                    }
                }
                is UiState.Error -> {
                    viewmodel.showError(uiState.errorMessage)
                }
            }
        }
        LoadingAnimation(
            isDisplayed = loading,
            modifier = Modifier.align(Alignment.Center)
        )
        ErrorModal(
            message = errMess,
            isDisplayed = errShow,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
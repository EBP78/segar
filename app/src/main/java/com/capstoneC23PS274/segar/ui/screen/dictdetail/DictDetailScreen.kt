package com.capstoneC23PS274.segar.ui.screen.dictdetail

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.capstoneC23PS274.segar.di.Injection
import com.capstoneC23PS274.segar.ui.common.UiState
import com.capstoneC23PS274.segar.ui.component.DetailTextItem
import com.capstoneC23PS274.segar.ui.component.ImageCarousell
import com.capstoneC23PS274.segar.ui.component.LoadingAnimation
import com.capstoneC23PS274.segar.ui.theme.MainGreen
import com.capstoneC23PS274.segar.ui.theme.Typography
import com.capstoneC23PS274.segar.utils.ViewModelFactory
import androidx.compose.runtime.getValue
import com.capstoneC23PS274.segar.ui.component.ErrorModal

@Composable
fun DictDetailScreen (
    dictItemId: String,
    modifier: Modifier = Modifier,
    viewmodel: DictDetailViewmodel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    context: Context = LocalContext.current
) {
    val loading by viewmodel.loading
    val errMess by viewmodel.errorMessage
    val errShow by viewmodel.errorShow
    Box(modifier = modifier.fillMaxSize()) {
        viewmodel.dictionaryData.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when(uiState) {
                is UiState.Loading -> {
                    viewmodel.getDictionaryDetail(dictItemId)
                }
                is UiState.Success -> {
                    if (uiState.data.data != null){
                        LazyColumn (
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            item {
                                // has to be changed to a list or make it only one image
                                val imageList = listOf(uiState.data.data.image.toString(),uiState.data.data.image.toString())
                                ImageCarousell(urlList = imageList)
                            }
                            item {
                                Text(
                                    text = uiState.data.data.name.toString(),
                                    style = Typography.h5,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                            item {
                                Text(
                                    text = uiState.data.data.scientificName.toString(),
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
                                DetailTextItem(title = "Famili", content = uiState.data.data.famili.toString())
                            }
                            item {
                                DetailTextItem(title = "Bagian yang dapat dikonsumsi", content = uiState.data.data.consumablePart.toString())
                            }
                            item {
                                DetailTextItem(title = "Tanah Asal", content = uiState.data.data.origin.toString())
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
                                DetailTextItem(title = "Informasi Lainnya", content = uiState.data.data.briefDesc.toString())
                            }
                        }
                    } else {
                        viewmodel.showError(uiState.data.message.toString())
                    }
                }
                is UiState.Error -> {
                    viewmodel.showError(uiState.errorMessage)
                    Toast.makeText(context, "gagal", Toast.LENGTH_SHORT).show()
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

@Composable
@Preview(showBackground = true)
fun ResultScreenPreview() {
    MaterialTheme {
        DictDetailScreen(dictItemId = "64748fc22ab453019ff486c8")
    }
}
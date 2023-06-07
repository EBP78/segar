package com.capstoneC23PS274.segar.ui.screen.result

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.capstoneC23PS274.segar.R
import com.capstoneC23PS274.segar.di.Injection
import com.capstoneC23PS274.segar.ui.theme.MainGreen
import com.capstoneC23PS274.segar.ui.theme.Typography
import com.capstoneC23PS274.segar.utils.ViewModelFactory
import androidx.compose.runtime.getValue
import com.capstoneC23PS274.segar.ui.common.UiState
import com.capstoneC23PS274.segar.ui.component.ErrorModal
import com.capstoneC23PS274.segar.ui.component.LoadingAnimation
import com.capstoneC23PS274.segar.utils.formatDate

@Composable
fun ResultScreen(
    checkId: String,
    modifier: Modifier = Modifier,
    viewmodel: ResultViewmodel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    context: Context = LocalContext.current
) {
    val loading by viewmodel.loading
    val errMess by viewmodel.errorMessage
    val errShow by viewmodel.errorShow
    Box(modifier = modifier.fillMaxSize()){
        viewmodel.result.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when(uiState) {
                is UiState.Loading -> {
                    viewmodel.getResult(checkId)
                }
                is UiState.Success -> {
                    if (uiState.data.data != null) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            AsyncImage(
                                model = ImageRequest.Builder(context)
                                    .data(uiState.data.data.image)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = "gambar sayur",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .width(225.dp)
                                    .height(350.dp)
                                    .padding(8.dp)
                                    .border(BorderStroke(3.dp, MainGreen))
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_check_circle_24),
                                contentDescription = "check icon",
                                tint = MainGreen,
                                modifier = Modifier
                                    .width(75.dp)
                                    .height(75.dp)
                            )
                            Text(
                                text = uiState.data.data.name.toString(),
                                style = Typography.h5,
                                fontWeight = FontWeight.Bold,
                                color = MainGreen
                            )
                            Text(
                                text = uiState.data.data.score.toString(),
                                style = Typography.h6,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = formatDate(uiState.data.data.createdAt.toString()),
                                style = Typography.subtitle1,
                            )
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

@Composable
@Preview(showBackground = true)
fun ResultScreenPreview() {
    MaterialTheme {
        ResultScreen(checkId = "001")
    }
}
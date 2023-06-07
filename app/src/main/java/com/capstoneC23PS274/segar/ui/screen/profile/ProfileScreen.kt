package com.capstoneC23PS274.segar.ui.screen.profile

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.capstoneC23PS274.segar.R
import com.capstoneC23PS274.segar.di.Injection
import com.capstoneC23PS274.segar.ui.common.UiState
import com.capstoneC23PS274.segar.ui.component.ErrorModal
import com.capstoneC23PS274.segar.ui.component.LoadingAnimation
import com.capstoneC23PS274.segar.ui.theme.MainGreen
import com.capstoneC23PS274.segar.utils.ViewModelFactory
import com.capstoneC23PS274.segar.utils.formatDate

@Composable
fun ProfileScreen(
    toFaq: () -> Unit,
    logout: () -> Unit,
    modifier: Modifier = Modifier,
    viewmodel: ProfileViewmodel = viewModel (
        factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
    ),
    context: Context = LocalContext.current
) {
    val loading by viewmodel.loading
    val errMess by viewmodel.errorMessage
    val errShow by viewmodel.errorShow
    Box (modifier = modifier.fillMaxSize()) {
        viewmodel.userData.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when(uiState) {
                is UiState.Loading -> {
                    viewmodel.getUserData()
                }
                is UiState.Success -> {
                    if (uiState.data.data != null){
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.username),
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = 20.sp
                            )
                            Text(
                                text = uiState.data.data.username.toString(),
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = stringResource(id = R.string.email),
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = 20.sp
                            )
                            Text(
                                text = uiState.data.data.email.toString(),
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = stringResource(id = R.string.join),
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = 20.sp
                            )
                            Text(
                                text = formatDate(uiState.data.data.joinedAt.toString()),
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Button(
                                onClick = toFaq,
                                colors = ButtonDefaults.buttonColors(backgroundColor = MainGreen),
                                enabled = false,
                                modifier = Modifier
                                    .widthIn(min = 150.dp)
                                    .padding(10.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.faq),
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                )
                            }
                            Button(
                                onClick = {
                                    viewmodel.logout()
                                    logout()
                                },
                                colors = ButtonDefaults.buttonColors(backgroundColor = MainGreen),
                                modifier = Modifier
                                    .widthIn(min = 150.dp)
                                    .padding(10.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.logout),
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                )
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


@Composable
@Preview(showBackground = true)
fun ProfilePreview() {
    MaterialTheme {
        ProfileScreen(toFaq = {}, logout = {})
    }
}
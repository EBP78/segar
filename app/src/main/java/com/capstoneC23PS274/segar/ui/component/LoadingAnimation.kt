package com.capstoneC23PS274.segar.ui.component

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.capstoneC23PS274.segar.ui.theme.SegarTheme

@Composable
fun LoadingAnimation(
    isDisplayed: Boolean,
    modifier: Modifier = Modifier
) {
    if (isDisplayed) {
        CircularProgressIndicator(
            color = MaterialTheme.colors.primary,
            modifier = modifier
        )
    }
}

@Composable
@Preview(showBackground = true)
fun LoadingAnimationPreview() {
    SegarTheme() {
        LoadingAnimation(isDisplayed = true)
    }
}
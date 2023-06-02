package com.capstoneC23PS274.segar.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstoneC23PS274.segar.ui.theme.SegarTheme

@Composable
fun LoadingAnimation(
    isDisplayed: Boolean,
    modifier: Modifier = Modifier
) {
    if (isDisplayed) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .border(
                    border = ButtonDefaults.outlinedBorder,
                    shape = RoundedCornerShape(4.dp)
                )
                .background(Color.White)
                .padding(10.dp)
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary,
            )
            Text(
                text = "Loading...",
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoadingAnimationPreview() {
    SegarTheme() {
        LoadingAnimation(isDisplayed = true)
    }
}
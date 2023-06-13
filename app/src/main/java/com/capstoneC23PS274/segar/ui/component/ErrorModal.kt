package com.capstoneC23PS274.segar.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstoneC23PS274.segar.R
import com.capstoneC23PS274.segar.ui.theme.SegarTheme
import com.capstoneC23PS274.segar.ui.theme.Typography

@Composable
fun ErrorModal(
    message: String,
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
            Icon(
                painter = painterResource(id = R.drawable.baseline_warning_amber_24),
                contentDescription = stringResource(R.string.error_icon),
                tint = Color.Red,
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
            )
            Text(
                text = message,
                textAlign = TextAlign.Center,
                style = Typography.subtitle2,
                modifier = Modifier.widthIn(max = 150.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ErrorModalPreview() {
    SegarTheme {
        ErrorModal(message = "gambar gagal diambil lorem ipsum dolor amety asdf asdf asdf ", isDisplayed = true)
    }
}
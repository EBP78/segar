package com.capstoneC23PS274.segar.ui.screen.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstoneC23PS274.segar.ui.theme.MainGreen

@Composable
fun ProfileScreen(
    toFaq: () -> Unit,
    logout: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Text(
                text = "Username",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 20.sp
            )
            Text(
                text = "Budi",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Email",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 20.sp
            )
            Text(
                text = "Budi205@gmail.com",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Join At",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 20.sp
            )
            Text(
                text = "1 May, 2023",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
            Button(
                onClick = toFaq,
                colors = ButtonDefaults.buttonColors(backgroundColor = MainGreen),
                modifier = Modifier
                    .widthIn(min = 150.dp)
                    .padding(10.dp)
            ) {
                Text(
                    text = "FAQ",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                )
            }
            Button(
                onClick = logout,
                colors = ButtonDefaults.buttonColors(backgroundColor = MainGreen),
                modifier = Modifier
                    .widthIn(min = 150.dp)
                    .padding(10.dp)
            ) {
                Text(
                    text = "Logout",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun ProfilePreview() {
    MaterialTheme {
        ProfileScreen(toFaq = {}, logout = {})
    }
}
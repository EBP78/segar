package com.capstoneC23PS274.segar.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstoneC23PS274.segar.R


val MainGreen = Color(0xFF7DC09C)
@Composable
fun FAQList(
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        border = BorderStroke(1.dp, MainGreen)

    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = "Question 1...",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 30.sp
            )

            Text(
                text = "Answer 1...",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 25.sp
            )
        }
        Row(modifier = Modifier.padding(8.dp)) {

                    Icon(
                        painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                        contentDescription = "Expandable Arrow",

                    )
            }
        }
    }



@Composable
@Preview(showBackground = true)
fun FAQPreview() {
    MaterialTheme {
        FAQList(
            modifier = Modifier
        )
    }
}
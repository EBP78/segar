package com.capstoneC23PS274.segar.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstoneC23PS274.segar.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.rotate


val MainGreen = Color(0xFF7DC09C)
@Composable
fun FAQListItem(
    question: String,
    answer: String,
    modifier: Modifier = Modifier,
) {
    var isOpen by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = modifier.fillMaxWidth(),
        border = BorderStroke(1.dp, MainGreen)

    ) {
        Box {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterStart)
            ) {
                Text(
                    text = question,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 30.sp
                )

                Text(
                    text = answer,
                    maxLines = if (!isOpen) 2 else 5,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 25.sp
                )
            }
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterEnd)
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                    contentDescription = "Expandable Arrow",
                    modifier = Modifier
                        .clickable {
                            isOpen = !isOpen
                        }
                        .rotate(
                            if (isOpen) {
                                90F
                            } else {
                                0F
                            }
                        )
                )
            }
        }
    }
}



@Composable
@Preview(showBackground = true)
fun FAQPreview() {
    MaterialTheme {
        FAQListItem(
            "Question 1...",
            "Answer 1... lorem ipsum dolor amet lorem ipsum dolor amet lorem ipsum dolor amet lorem ipsum dolor amet lorem ipsum dolor amet lorem ipsum dolor amet lorem ipsum dolor amet lorem ipsum dolor amet lorem ipsum dolor amet lorem ipsum dolor amet lorem ipsum dolor amet"
        )
    }
}
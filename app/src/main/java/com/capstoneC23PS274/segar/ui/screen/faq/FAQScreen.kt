package com.capstoneC23PS274.segar.ui.screen.faq

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.capstoneC23PS274.segar.ui.component.FAQListItem

@Composable
fun FAQScreen (
    modifier: Modifier = Modifier
) {
    // getting data from viewmodel
    // contoh bentuk item nantinya
    FAQListItem(
        "Question 1...",
        "Answer 1... lorem ipsum dolor amet lorem ipsum dolor amet lorem ipsum dolor amet lorem ipsum dolor amet lorem ipsum dolor amet lorem ipsum dolor amet lorem ipsum dolor amet lorem ipsum dolor amet lorem ipsum dolor amet lorem ipsum dolor amet lorem ipsum dolor amet",
        modifier = Modifier.padding(10.dp)
    )
}
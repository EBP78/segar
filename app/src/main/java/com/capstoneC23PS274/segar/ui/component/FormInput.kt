package com.capstoneC23PS274.segar.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Typeface
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstoneC23PS274.segar.ui.screen.login.MainGreen
import com.capstoneC23PS274.segar.ui.theme.Typography

@Composable
fun FormInput(
    query: String,
    onQueryChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        textStyle = Typography.body2,
        placeholder = {
            Text(
                text = placeholder,
                fontSize = 18.sp
            )
        },
        modifier = modifier
            .padding(1.dp)
            .fillMaxWidth()
            .heightIn(min = 18.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(BorderStroke(1.dp, MainGreen))
    )
    Spacer(modifier = modifier.height(15.dp))
}

@Composable
@Preview(showBackground = true)
fun FormInputPreview() {
    MaterialTheme {
        FormInput(query = "", onQueryChange = {}, placeholder = "isi")
    }
}
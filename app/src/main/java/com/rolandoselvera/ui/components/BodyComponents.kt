package com.rolandoselvera.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainTitle(
    modifier: Modifier,
    title: String,
    textAlign: TextAlign? = TextAlign.Center,
    fontSize: TextUnit = 14.sp
) {
    Text(
        text = title,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        modifier = modifier,
        textAlign = textAlign,
        fontSize = fontSize
    )
}

@Composable
fun SearchTextField(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    onClickSearchIcon: () -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        trailingIcon = {
            Icon(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        onClickSearchIcon()
                    },
                imageVector = Icons.Default.Search,
                contentDescription = ""
            )
        },
        singleLine = true,
        maxLines = 1,
        modifier = modifier
    )
}

@Composable
fun SpaceView(padding: Dp) {
    Spacer(modifier = Modifier.padding(padding))
}

@Preview(showBackground = true)
@Composable
fun MainTitlePreview() {
    SearchTextField(
        modifier = Modifier, value = "", onValueChange = {}, label = "", {}
    )
}
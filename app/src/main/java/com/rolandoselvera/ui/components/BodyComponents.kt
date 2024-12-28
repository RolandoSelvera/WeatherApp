package com.rolandoselvera.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
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
fun MainTextField(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        modifier = modifier
    )
}

@Composable
fun SpaceView(padding: Dp) {
    Spacer(modifier = Modifier.padding(padding))
}
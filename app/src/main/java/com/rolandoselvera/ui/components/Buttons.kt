package com.rolandoselvera.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.rolandoselvera.ui.theme.Secondary

@Composable
fun FloatButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add"
        )
    }
}

@Composable
fun MainButton(
    modifier: Modifier,
    icon: Painter?,
    text: String,
    enabled: Boolean = false,
    onClick: () -> Unit
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = Secondary
        ),
        onClick = onClick,
        contentPadding = PaddingValues(8.dp),
        enabled = enabled,
        modifier = modifier
    ) {
        Row {
            if (icon != null) {
                Icon(painter = icon, contentDescription = "", modifier = Modifier.size(24.dp))
            }
            Text(text = text)
        }
    }
}

@Composable
fun HeaderIconButton(icon: ImageVector, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(imageVector = icon, contentDescription = null, tint = Color.White)
    }
}

@Composable
fun CircleButton(
    icon: Painter,
    enabled: Boolean = false,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        contentPadding = PaddingValues(8.dp),
        enabled = enabled,
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        Icon(painter = icon, contentDescription = "", modifier = Modifier.size(24.dp))
    }
}
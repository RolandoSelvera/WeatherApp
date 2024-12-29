package com.rolandoselvera.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SimpleDialog(
    isVisible: Boolean,
    title: String,
    message: String,
    buttonText: String,
    onDismiss: () -> Unit
) {
    if (isVisible) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(text = title, style = MaterialTheme.typography.titleMedium)
            },
            text = {
                Text(text = message, style = MaterialTheme.typography.bodyMedium)
            },
            confirmButton = {
                Button(onClick = onDismiss) {
                    Text(text = buttonText)
                }
            }
        )
    }
}

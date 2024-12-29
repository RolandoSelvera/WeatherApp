package com.rolandoselvera.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rolandoselvera.R

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


@Composable
fun ConfirmationDialog(
    isVisible: Boolean,
    title: String,
    message: String,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
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
                Button(onClick = {
                    onConfirm()
                    onDismiss()
                }) {
                    Text(text = stringResource(R.string.accept))
                }
            },
            dismissButton = {
                Button(onClick = {
                    onCancel()
                    onDismiss()
                }) {
                    Text(text = stringResource(R.string.cancel))
                }
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewDialog() {
    ConfirmationDialog(
        isVisible = true,
        title = "Title",
        message = "Message",
        onConfirm = {},
        onCancel = {},
        onDismiss = {}
    )
}
package com.example.belajarandroid.helper

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

enum class DialogType {
    SUCCESS, ERROR
}

@Composable
fun MessageDialog(
    type: DialogType,
    message: String,
    onDismiss: () -> Unit
) {
    var isVisible by remember { mutableStateOf(true) }

    if (isVisible) {
        Dialog(onDismissRequest = { isVisible = false; onDismiss() }) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 6.dp
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(0.85f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val icon = if (type == DialogType.SUCCESS) Icons.Outlined.CheckCircle else Icons.Outlined.Warning
                    val iconColor = if (type == DialogType.SUCCESS) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error

                    Icon(
                        imageVector = icon,
                        contentDescription = if (type == DialogType.SUCCESS) "Success" else "Error",
                        modifier = Modifier.size(64.dp),
                        tint = iconColor
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = message,
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { isVisible = false; onDismiss() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text("OK", color = Color.White)
                    }
                }
            }
        }
    }
}
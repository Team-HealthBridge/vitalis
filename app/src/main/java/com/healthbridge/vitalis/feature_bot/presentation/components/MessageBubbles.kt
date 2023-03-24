package com.healthbridge.vitalis.feature_bot.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun UserMessageBubble(message: String) {
    Box(modifier = Modifier
        .padding(8.dp)
        .background(color = MaterialTheme.colorScheme.primary, shape = MaterialTheme.shapes.medium)
        .height(48.dp)
    ) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserMessageBubblePreview() {
    UserMessageBubble(message = "I have been having a headache for the past 3 days.")
}
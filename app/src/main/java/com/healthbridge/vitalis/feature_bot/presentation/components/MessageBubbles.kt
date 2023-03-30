package com.healthbridge.vitalis.feature_bot.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.healthbridge.vitalis.R

@Composable
fun UserMessageBubble(message: String, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = MaterialTheme.shapes.medium
            )
            .padding(8.dp)

    ) {

        Text(
            text = message,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.align(Alignment.End)
        )
    }
}

@Composable
fun BotMessageBubble(message: String, modifier: Modifier = Modifier) {
    Row {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher),
                contentDescription = "Bot Logo",
                modifier = Modifier
                    .padding(8.dp)
                    .size(32.dp)
            )
            Column {
                Text(
                    "Vitalis",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    "10 min ago",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }
    }
    Box(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.secondary,
                shape = MaterialTheme.shapes.medium
            )
            .height(48.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                text = message,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}


// Create Bubbles using a mutable list
@Composable
fun CreateMessageBubbles(userMessages: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        for (message in userMessages) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.End
            ) {
                UserMessageBubble(message = message)
                Spacer(modifier = Modifier.height(8.dp))
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                BotMessageBubble(message = "Hello, I'm Vitalis. I'm still waiting to be set up!! ")
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UserMessageBubblePreview() {
    Column {
        CreateMessageBubbles(userMessages = listOf("Hello", "How are you?", "I'm fine thanks"))
    }
}
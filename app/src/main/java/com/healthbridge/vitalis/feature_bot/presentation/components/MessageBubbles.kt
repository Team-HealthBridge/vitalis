package com.healthbridge.vitalis.feature_bot.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.healthbridge.vitalis.R
import com.healthbridge.vitalis.feature_bot.data.remote.responses.Activity
import com.healthbridge.vitalis.feature_bot.data.remote.responses.Attachment
import java.time.Instant


@Composable
fun UserMessageBubble(
    message: String,
) {
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
fun BotMessageBubble(message: String, time: String) {
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
                    calculateTimeDifference(time),
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
            .wrapContentHeight()
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
fun CreateMessageBubbles(activities: List<Activity>, onSend: (String) -> Unit) {

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f)
            .padding(8.dp),
        content = {
            activities.forEach {
                if (it.from.name != "vitalis") {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalAlignment = Alignment.End
                        ) {
                            UserMessageBubble(message = it.text)
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                } else {
                    if (it.attachments == null || it.attachments.isEmpty()) {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                horizontalAlignment = Alignment.Start
                            ) {
                                BotMessageBubble(message = it.text, time = it.timestamp)
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    } else {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                horizontalAlignment = Alignment.Start
                            ) {
                                BotMessageBubble(message = it.text, time = it.timestamp)
                                Spacer(modifier = Modifier.height(8.dp))
                                BotDialogBox(attachment = it.attachments[0], onSend = onSend)
                                Spacer(modifier = Modifier.height(8.dp))
                            }

                        }

                    }
                }

            }

        }
    )
}


fun calculateTimeDifference(time: String): String {
    val milliseconds = getMillisecondsFromTimestamp(time)
    val timeDifference = System.currentTimeMillis() - milliseconds
    val seconds = timeDifference / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24
    val weeks = days / 7
    val months = days / 30
    val years = days / 365

    return when {
        seconds < 60 -> {
            "$seconds seconds ago"
        }
        minutes < 60 -> {
            "$minutes minutes ago"
        }
        hours < 24 -> {
            "$hours hours ago"
        }
        days < 7 -> {
            "$days days ago"
        }
        weeks < 4 -> {
            "$weeks weeks ago"
        }
        months < 12 -> {
            "$months months ago"
        }
        else -> {
            "$years years ago"
        }
    }
}

fun getMillisecondsFromTimestamp(timestamp: String): Long {
    val instant = Instant.parse(timestamp)
    return instant.toEpochMilli()
}

@Composable
fun BotDialogBox(attachment: Attachment, onSend: (String) -> Unit) {

    if (attachment.contentType == "application/vnd.microsoft.card.hero") {
        val (selectedOption, onOptionSelected) = remember { mutableStateOf(attachment.content.buttons[0]) }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = MaterialTheme.shapes.extraLarge
                )
            ,

            ) {
            attachment.content.buttons.forEachIndexed { index, button ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .selectable(
                            selected = (button.title == selectedOption.value),
                            onClick = {
                                onOptionSelected(button)
                                println("Row selected is: $selectedOption")
                            },
                            role = Role.RadioButton
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val purple = MaterialTheme.colorScheme.primaryContainer
                    Text(
                        text = "${index + 1}",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .padding(16.dp)
                            .drawBehind {
                                drawCircle(
                                    color = purple,
                                    radius = this.size.maxDimension
                                )
                            },
                    )
                    Text(
                        text = button.title,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    RadioButton(
                        selected = (button.title == selectedOption.value),
                        onClick = {
                            onOptionSelected(button)
                            println("Radio button selected is: $selectedOption")
                        }
                    )
                }
            }
            Button(
                onClick = {
                    onSend(selectedOption.value)
                },
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.End)
            ) {
                Text(text = "Submit")
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),

            ) {

            val selectedChoices: MutableState<List<String>> = remember { mutableStateOf(mutableListOf()) }
            attachment.content.body[0].items[0].choices.forEachIndexed { index, choice ->

                val (checkedState, onStateChange) = remember { mutableStateOf(false) }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .toggleable(
                            value = checkedState,
                            onValueChange = {
                                onStateChange(!checkedState)
                                if (checkedState) {
                                    selectedChoices.value = selectedChoices.value.filter { it != choice.title }
                                } else {
                                    selectedChoices.value = selectedChoices.value.plus(choice.title)
                                }
                                println("Selected choices are: ${selectedChoices.value}")
                                println("Selected choices are: ${selectedChoices.value.size}")
                                            },
                            role = Role.Checkbox
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val purple = MaterialTheme.colorScheme.primaryContainer
                    Text(
                        text = "${index + 1}",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .padding(16.dp)
                            .drawBehind {
                                drawCircle(
                                    color = purple,
                                    radius = this.size.maxDimension
                                )
                            },
                    )
                    Text(
                        text = choice.title,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    Checkbox(
                        checked = checkedState,
                        onCheckedChange = { onStateChange(!checkedState)
                            if (checkedState) {
                                selectedChoices.value = selectedChoices.value.filter { it != choice.title }
                            } else {
                                selectedChoices.value = selectedChoices.value + choice.title
                            }
                            println("Selected choices are: ${selectedChoices.value}")
                        }
                    )
                }
            }
            Button(
                onClick = {
                    onSend(selectedChoices.value.joinToString(","))
                },
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.End)
            ) {
                Text(text = "Submit")
            }
        }
    }
}




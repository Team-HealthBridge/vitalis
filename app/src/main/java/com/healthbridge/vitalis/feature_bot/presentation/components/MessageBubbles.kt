package com.healthbridge.vitalis.feature_bot.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.healthbridge.vitalis.R
import com.healthbridge.vitalis.feature_bot.data.remote.responses.*
import java.time.Instant


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
fun BotMessageBubble(message: String, modifier: Modifier = Modifier, time: String) {
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
fun CreateMessageBubbles(activities: List<Activity>, choices: List<String>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f)
            .padding(8.dp),
        content = {
            items(activities.size) { index ->
                if (activities[index].from.name != "vitalis") {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        UserMessageBubble(message = activities[index].text)
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        BotMessageBubble(
                            message = activities[index].text,
                            time = activities[index].timestamp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        if(activities[index].attachments != null){
                            if(activities[index].attachments.isNotEmpty()) {
                                BotDialogBox(choices)
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
fun BotDialogBox(options: List<String>){
    Column(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = MaterialTheme.shapes.extraLarge
                )
                .padding(8.dp)
        ) {
            Text(
                text = "How can I help you?",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Below is a set options of what I can assist you with. Please select one to continue.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally)
            )
            listOptions(options)
        }
}


@Composable
fun listOptions(list: List<String>) {
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(list[0]) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),

    ){
        list.forEachIndexed { index, item ->
            var state by remember { mutableStateOf(false) }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .selectable(
                        selected = (item == selectedOption),
                        onClick = {
                            onOptionSelected(item)
                            println("Row selected is: $item")
                                  },
                        role = Role.RadioButton
                    )
                ,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                var purple = MaterialTheme.colorScheme.primaryContainer
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
                    text = item,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                RadioButton(selected = (item == selectedOption), onClick = {
                    onOptionSelected(item)
                    println("Radio button selected is: $item")
                })
            }
        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.End)
        ) {
            Text(text = "Submit")
        }
    }
}




@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UserMessageBubblePreview() {
    Column {
        var activity1: Activity = Activity(
            id = "JNw2QqsiUMB4O7bP4unFkL-fr|0000000",
            timestamp = "2023-03-31T13:21:29.1794343Z",
            serviceUrl = "https://directline.botframework.com/",
            channelId = "directline",
            conversation = Conversation(id = "JNw2QqsiUMB4O7bP4unFkL-fr" ),
            type = "message",
            text = "Hello",
            from = From(id = "user2", name = "user2"),
            attachments = listOf(),
            inputHint = "acceptingInput",
            speak = "Hello", 
            replyToId = "JNw2QqsiUMB4O7bP4unFkL-fr|0000000",
            locale = "en-US",
            channelData = ChannelData(submitChoicesButtonText = "Submit"),
        )


        
        var button1 = Button(title = "Symptoms", type = "imBack", value = "Symptoms")
        var button2 = Button(title = "Treatments", type = "imBack", value = "Treatments")
        var button3 = Button(title = "Healthy Habits", type = "imBack", value = "Healthy Habits")
        var content = Content(
            `$schema` = "http://adaptivecards.io/schemas/adaptive-card.json",
            buttons = listOf(button1, button2, button3),
            actions = listOf(),
            body = listOf(),
            type = "AdaptiveCard",
            version = "1.0",
        )
        
        var attachment = Attachment(
            contentType = "application/vnd.microsoft.card.hero",
            content = content
        )

        var choices = listOf("Symptoms", "Treatments", "Healthy Habits")
        
        var activity2 = Activity(
            type = "message",
            id = "JNw2QqsiUMB4O7bP4unFkL-fr|0000001",
            timestamp = "2023-03-31T13:21:29.1794343Z",
            channelId = "directline",
            from = From(id = "vitalis-bcgiqah", name = "vitalis"),
            conversation = Conversation(id = "JNw2QqsiUMB4O7bP4unFkL-fr"),
            locale = "en-US",
            text = "Hello! I'm Vitalis, your personal health bot. I'm here to help answer your basic health questions and provide you with information on a wide range of topics. Whether you have questions about symptoms, treatments, or healthy habits, I'm here to assist you. Let's get started!",
            speak = "Hello! I'm Vitalis, your personal health bot. I'm here to help answer your basic health questions and provide you with information on a wide range of topics. Whether you have questions about symptoms, treatments, or healthy habits, I'm here to assist you. Let's get started!",
            inputHint = "acceptingInput",
            attachments = listOf(attachment),
            replyToId = "JNw2QqsiUMB4O7bP4unFkL-fr|0000000",
            serviceUrl = "",
            channelData = ChannelData(submitChoicesButtonText = "Submit"),
        )
        CreateMessageBubbles(activities = listOf(activity1, activity2), choices = choices)
    }
}
package com.healthbridge.vitalis.feature_bot.data.remote.responses

data class ReceiveActivities(
    val activities: List<Activity>,
    val watermark: String
)

data class Activity(
    val attachments: List<Attachment>,
    val channelId: String,
    val conversation: Conversation,
    val from: From,
    val id: String,
    val inputHint: String,
    val locale: String,
    val replyToId: String,
    val serviceUrl: String,
    val speak: String,
    val suggestedActions: SuggestedActions,
    val text: String,
    val timestamp: String,
    val type: String
)

data class Attachment(
    val content: Content,
    val contentType: String
)

data class Conversation(
    val id: String
)

data class From(
    val id: String,
    val name: String
)

data class SuggestedActions(
    val actions: List<Action>
)

data class Content(
    val buttons: List<Button>
)

data class Button(
    val title: String,
    val type: String,
    val value: String
)

data class Action(
    val title: String,
    val type: String,
    val value: String
)
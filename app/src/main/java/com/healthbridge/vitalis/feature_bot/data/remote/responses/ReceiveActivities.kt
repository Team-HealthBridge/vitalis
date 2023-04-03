package com.healthbridge.vitalis.feature_bot.data.remote.responses

data class ReceiveActivities(
    val activities: List<Activity>,
    val watermark: String
)

data class Activity(
    val attachments: List<Attachment>,
    val channelData: ChannelData,
    val channelId: String,
    val conversation: Conversation,
    val from: From,
    val id: String,
    val inputHint: String,
    val locale: String,
    val replyToId: String,
    val serviceUrl: String,
    val speak: String,
    val text: String,
    val timestamp: String,
    val type: String
)

data class Attachment(
    val content: Content,
    val contentType: String
)

data class ChannelData(
    val submitChoicesButtonText: String
)

data class Conversation(
    val id: String
)

data class From(
    val id: String,
    val name: String
)

data class Content(
    val `$schema`: String,
    val actions: List<Action>,
    val body: List<Body>,
    val buttons: List<Button>,
    val type: String,
    val version: String
)

data class Action(
    val title: String,
    val type: String
)

data class Body(
    val items: List<Item>,
    val type: String
)

data class Button(
    val title: String,
    val type: String,
    val value: String
)

data class Item(
    val choices: List<Choice>,
    val id: String,
    val isMultiSelect: Boolean,
    val style: String,
    val type: String,
    val wrap: Boolean
)

data class Choice(
    val title: String,
    val value: String
)
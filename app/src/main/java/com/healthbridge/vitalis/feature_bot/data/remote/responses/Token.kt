package com.healthbridge.vitalis.feature_bot.data.remote.responses

data class Token(
    val conversationId: String,
    val expires_in: Int,
    val token: String
)
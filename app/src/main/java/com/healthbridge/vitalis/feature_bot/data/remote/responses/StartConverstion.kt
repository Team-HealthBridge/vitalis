package com.healthbridge.vitalis.feature_bot.data.remote.responses

data class StartConverstion(
    val conversationId: String,
    val expires_in: Int,
    val referenceGrammarId: String,
    val streamUrl: String,
    val token: String
)
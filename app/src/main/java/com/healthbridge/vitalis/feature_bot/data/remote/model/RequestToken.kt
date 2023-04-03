package com.healthbridge.vitalis.feature_bot.data.remote.model

data class RequestToken(
    val user: User
)

data class User(
    val id: String,
    val name: String
)
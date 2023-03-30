package com.healthbridge.vitalis.feature_bot.data.remote.model


data class UserActivity(
    val from: UserFrom,
    val locale: String,
    val text: String,
    val type: String
)

data class UserFrom(
    val id: String
)
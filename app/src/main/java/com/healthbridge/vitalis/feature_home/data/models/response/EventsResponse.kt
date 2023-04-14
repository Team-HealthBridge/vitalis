package com.healthbridge.vitalis.feature_home.data.models.response

data class EventsResponse(
    val description: String,
    val endDate: String,
    val id: Int,
    val learnMoreUrl: String,
    val pictureUrl: String,
    val startDate: String,
    val title: String
)
package com.healthbridge.vitalis.feature_home.data.repository

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.healthbridge.vitalis.feature_home.data.API.ApiClient
import com.healthbridge.vitalis.feature_home.data.models.response.Content
import com.healthbridge.vitalis.feature_home.data.models.response.EventsResponse

class HealthBitsRepository {

    val api = ApiClient().getApi()

    val healthBits: MutableState<List<Content>> = mutableStateOf(listOf())
    val event: MutableState<EventsResponse> = mutableStateOf(EventsResponse("","",0,"","","",""))
    val category: MutableState<List<String>> = mutableStateOf(listOf())


    suspend fun getHealthBits() {
        val returnedHealthBits = api.getHealthBits()
        healthBits.value = returnedHealthBits.content
    }

    suspend fun getCategories(){
        val returnedCategories = api.getCategories()
        category.value = returnedCategories
    }

    suspend fun getHealthBitsByCategory(category: String) = api.getHealthBitsByCategory(category).content

    suspend fun getEvents() {
        val returnedEvents = api.getEvents()
        event.value = returnedEvents
    }




}
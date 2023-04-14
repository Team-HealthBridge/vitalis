package com.healthbridge.vitalis.feature_home.data.API

import com.healthbridge.vitalis.feature_home.data.models.response.EventsResponse
import com.healthbridge.vitalis.feature_home.data.models.response.HealthBits
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    //Get Bits
    @GET("health-bits/random")
    suspend fun getHealthBits(): HealthBits

    // Get Categories
    @GET("categories")
    suspend fun getCategories(): List<String>

    // Get Bits by Category
    @GET("health-bits")
    suspend fun getHealthBitsByCategory(
        @Query("category") category: String
    ): List<HealthBits>

    // Get Events
    @GET("health-events")
    suspend fun getEvents(): EventsResponse
}
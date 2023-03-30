package com.healthbridge.vitalis.feature_bot.data.remote

import com.healthbridge.vitalis.feature_bot.data.remote.model.UserActivity
import com.healthbridge.vitalis.feature_bot.data.remote.responses.ReceiveActivities
import com.healthbridge.vitalis.feature_bot.data.remote.responses.SendActivity
import com.healthbridge.vitalis.feature_bot.data.remote.responses.StartConverstion
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService{

    // START CONVERSATION
    @POST("/v3/directline/conversations")
    suspend fun startConversation(): Flow<StartConverstion>

    // SEND ACTIVITY
    @POST("/v3/directline/conversations/{conversationId}/activities")
    suspend fun sendActivity(
        @Path("conversationId") conversationId: String,
        @Body userActivity: UserActivity): SendActivity

    // RECEIVE ACTIVITIES
    @GET("/v3/directline/conversations/{conversationId}/activities")
    suspend fun receiveActivities(
        @Path("conversationId") conversationId: String): Flow<List<ReceiveActivities>>

}
package com.healthbridge.vitalis.feature_bot.data.remote

import com.healthbridge.vitalis.feature_bot.data.remote.model.RequestToken
import com.healthbridge.vitalis.feature_bot.data.remote.model.UserActivity
import com.healthbridge.vitalis.feature_bot.data.remote.responses.ReceiveActivities
import com.healthbridge.vitalis.feature_bot.data.remote.responses.SendActivity
import com.healthbridge.vitalis.feature_bot.data.remote.responses.StartConverstion
import com.healthbridge.vitalis.feature_bot.data.remote.responses.Token
import com.healthbridge.vitalis.util.Constants
import kotlinx.coroutines.flow.Flow
import retrofit2.http.*

interface ApiService{

    val token: String
        get() = Constants.TOKEN

    // GET TOKEN
    @POST("/v3/directline/tokens/generate")
    suspend fun getToken(
        @Body requestToken: RequestToken,
    ): Token

    // REFRESH TOKEN
    @POST("/v3/directline/tokens/refresh")
    suspend fun refreshToken(
    ): Token


    // START CONVERSATION
    @POST("/v3/directline/conversations")
    suspend fun startConversation(
    ): StartConverstion

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
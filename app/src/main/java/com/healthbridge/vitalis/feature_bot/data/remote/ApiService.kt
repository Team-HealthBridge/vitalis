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


    // GET TOKEN
    @POST("/v3/directline/tokens/generate")
    suspend fun getToken(
        @Header ("Authorization") token: String,
        @Body requestToken: RequestToken,
    ): Token

    // REFRESH TOKEN
    @POST("/v3/directline/tokens/refresh")
    suspend fun refreshToken(
        @Header("Authorization") token: String
    ): Token


    // START CONVERSATION
    @POST("/v3/directline/conversations")
    suspend fun startConversation(
        @Header("Authorization") token: String
    ): StartConverstion

    // SEND ACTIVITY
    @POST("/v3/directline/conversations/{conversationId}/activities")
    suspend fun sendActivity(
        @Header("Authorization") token: String,
        @Path("conversationId") conversationId: String,
        @Body userActivity: UserActivity): SendActivity

    // RECEIVE ACTIVITIES
    @GET("/v3/directline/conversations/{conversationId}/activities")
    suspend fun receiveActivities(
        @Header("Authorization") token: String,
        @Path("conversationId") conversationId: String): ReceiveActivities

}
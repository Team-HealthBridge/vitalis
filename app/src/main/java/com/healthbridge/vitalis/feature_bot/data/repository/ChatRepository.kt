package com.healthbridge.vitalis.feature_bot.data.repository

import com.healthbridge.vitalis.feature_bot.data.remote.ApiService
import com.healthbridge.vitalis.feature_bot.data.remote.model.RequestToken
import com.healthbridge.vitalis.feature_bot.data.remote.model.UserActivity
import com.healthbridge.vitalis.feature_bot.data.remote.responses.ReceiveActivities
import com.healthbridge.vitalis.feature_bot.data.remote.responses.Token
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ChatRepository @Inject constructor(private val api: ApiService) {


    suspend fun getToken(requestToken: RequestToken): Token {
        return api.getToken(requestToken)
    }

    suspend fun refreshToken(): Token {
        return api.refreshToken()
    }

    suspend fun startConversation(): String {
        return api.startConversation().conversationId
    }

    suspend fun sendActivity(conversationId: String, userActivity: UserActivity): String {
        return api.sendActivity(conversationId, userActivity).id
    }

    suspend fun receiveActivities(conversationId: String): Flow<List<ReceiveActivities>> {
        return api.receiveActivities(conversationId)
    }

}


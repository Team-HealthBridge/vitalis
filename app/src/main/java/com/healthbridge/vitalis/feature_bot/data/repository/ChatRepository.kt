package com.healthbridge.vitalis.feature_bot.data.repository

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.healthbridge.vitalis.feature_bot.data.remote.ApiService
import com.healthbridge.vitalis.feature_bot.data.remote.model.RequestToken
import com.healthbridge.vitalis.feature_bot.data.remote.model.User
import com.healthbridge.vitalis.feature_bot.data.remote.model.UserActivity
import com.healthbridge.vitalis.feature_bot.data.remote.responses.Activity
import com.healthbridge.vitalis.feature_bot.data.remote.responses.Token
import com.healthbridge.vitalis.util.Constants
import javax.inject.Inject

class ChatRepository @Inject constructor(private val api: ApiService) {

    val activityState:MutableState<List<Activity>> = mutableStateOf(emptyList())
    var choicesState:MutableState<List<String>> = mutableStateOf(emptyList())

    lateinit var conversationId:String
    private var token:String? = null


    private suspend fun getToken(): String {
        if (this.token == null) {
            val requestToken = RequestToken(
                user = User(
                    id = "user2",
                    name = "HealthBridge"
                )
            )
            this.token = api.getToken(Constants.TOKEN, requestToken).token
        }
            return "Bearer ${this.token}"
    }

    private suspend fun refreshToken(): Token {
        return api.refreshToken(getToken())
    }

    suspend fun startConversation() {
        conversationId = api.startConversation(getToken()).conversationId

    }

    suspend fun sendActivity(userActivity: UserActivity) {
        api.sendActivity(getToken(),conversationId, userActivity).id
        receiveActivities()

    }

    suspend fun receiveActivities() {
       val activities = api.receiveActivities(getToken(), conversationId)
        activityState.value = activities.activities
        if (activities.activities.last().attachments != null) {
           if(activities.activities.last().attachments[0].contentType == "application/vnd.microsoft.card.hero") {
               choicesState.value = activities.activities.last().attachments.first().content.buttons.map { it.title}
           } else if (activities.activities.last().attachments[0].contentType == "application/vnd.microsoft.card.adaptive") {
               choicesState.value = activities.activities.last().attachments[0].content.body[0].items.map { it.choices.map { it.title } }.flatten()
           }
        } else {
            choicesState.value = emptyList()
        }
    }

}


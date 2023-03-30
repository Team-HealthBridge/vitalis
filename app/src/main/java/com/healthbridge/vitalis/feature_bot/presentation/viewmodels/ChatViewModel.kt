package com.healthbridge.vitalis.feature_bot.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import com.healthbridge.vitalis.feature_bot.data.remote.model.RequestToken
import com.healthbridge.vitalis.feature_bot.data.remote.model.User
import com.healthbridge.vitalis.feature_bot.data.remote.model.UserActivity
import com.healthbridge.vitalis.feature_bot.data.remote.model.UserFrom
import com.healthbridge.vitalis.feature_bot.data.repository.ChatRepository
import com.healthbridge.vitalis.feature_bot.di.AppModule
import com.healthbridge.vitalis.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val chatRepository: ChatRepository, savedStateHandle: SavedStateHandle) : ViewModel() {

    private var conversationId: String? = "2APOxIvRhaeJySA8QzUhHg-fr"

    fun getToken() {
        CoroutineScope(Dispatchers.Main).launch {
            if ( Constants.TOKEN.length > 55) {
                Constants.TOKEN = chatRepository.refreshToken().token
            } else{
                val requestToken = RequestToken(
                    user = User(
                        id = "user2",
                        name = "HealthBridge"
                    )
                )
                Constants.TOKEN = chatRepository.getToken(requestToken).token
            }
        }
    }

    fun startConversation() {
        CoroutineScope(Dispatchers.Default).launch {
           conversationId = chatRepository.startConversation()
            println(conversationId)
        }
    }

    fun sendUserInput(input: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val userActivity = UserActivity(
                from = UserFrom(id = "Terry Mochire"),
                locale = "en-US",
                text = input,
                type = "message"
            )
            chatRepository.sendActivity(conversationId!!, userActivity)
        }

    }

    val botResponse = {
        CoroutineScope(Dispatchers.IO).launch {
            chatRepository.receiveActivities(conversationId!!)
        }
    }


}


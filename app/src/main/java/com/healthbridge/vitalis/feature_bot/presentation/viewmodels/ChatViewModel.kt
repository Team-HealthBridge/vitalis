package com.healthbridge.vitalis.feature_bot.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.healthbridge.vitalis.feature_bot.data.remote.model.UserActivity
import com.healthbridge.vitalis.feature_bot.data.remote.model.UserFrom
import com.healthbridge.vitalis.feature_bot.data.remote.responses.Activity
import com.healthbridge.vitalis.feature_bot.data.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val chatRepository: ChatRepository, savedStateHandle: SavedStateHandle) : ViewModel() {


    val botResponse: MutableState<List<Activity>> = chatRepository.activityState

    fun sendUserInput(input: String) {
        viewModelScope.launch {
            val userActivity = UserActivity(
                from = UserFrom(id = "HealthBridge"),
                locale = "en-US",
                text = input,
                type = "message"
            )
            chatRepository.sendActivity(userActivity)
        }

    }

    init {
        viewModelScope.launch {
            chatRepository.startConversation()
        }
    }


}


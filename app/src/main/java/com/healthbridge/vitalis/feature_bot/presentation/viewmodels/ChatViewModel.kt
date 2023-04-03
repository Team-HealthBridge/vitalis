package com.healthbridge.vitalis.feature_bot.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.healthbridge.vitalis.feature_bot.data.remote.model.RequestToken
import com.healthbridge.vitalis.feature_bot.data.remote.model.User
import com.healthbridge.vitalis.feature_bot.data.remote.model.UserActivity
import com.healthbridge.vitalis.feature_bot.data.remote.model.UserFrom
import com.healthbridge.vitalis.feature_bot.data.remote.responses.Activity
import com.healthbridge.vitalis.feature_bot.data.remote.responses.ReceiveActivities
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


    val botResponse: MutableState<List<Activity>> = chatRepository.activityState
    val botChoices: MutableState<List<String>> = chatRepository.choicesState

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


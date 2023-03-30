package com.healthbridge.vitalis.feature_bot.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import com.healthbridge.vitalis.feature_bot.data.remote.model.UserActivity
import com.healthbridge.vitalis.feature_bot.data.remote.model.UserFrom
import com.healthbridge.vitalis.feature_bot.data.repository.ChatRepository
import com.healthbridge.vitalis.feature_bot.di.AppModule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val chatRepository: ChatRepository, savedStateHandle: SavedStateHandle) : ViewModel() {

    private val conversationId: String = "CeigNsAD8QpLXCI2tAHW84-fr"

    fun sendUserInput(input: String) {
        CoroutineScope(Dispatchers.Default).launch {
            val userActivity = UserActivity(
                from = UserFrom(id = "user1"),
                locale = "en-US",
                text = input,
                type = "message"
            )
            chatRepository.sendActivity(conversationId!!, userActivity)
        }

    }

    val botResponse = {
        CoroutineScope(Dispatchers.Default).launch {
            chatRepository.receiveActivities(conversationId!!)
        }
    }



}


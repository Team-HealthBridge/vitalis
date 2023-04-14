package com.healthbridge.vitalis.feature_bot.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.healthbridge.vitalis.R
import com.healthbridge.vitalis.feature_bot.presentation.components.CreateMessageBubbles
import com.healthbridge.vitalis.feature_bot.presentation.components.MessageInput
import com.healthbridge.vitalis.feature_bot.presentation.viewmodels.ChatViewModel
import com.healthbridge.vitalis.ui.theme.VitalisTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ChatScreen : ComponentActivity() {

    private val chatViewModel: ChatViewModel by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VitalisTheme {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(
                                    "Chat",
                                    color = MaterialTheme.colorScheme.primary,
                                )
                            },
                            Modifier.background(color = MaterialTheme.colorScheme.tertiary),
                            navigationIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
                                    contentDescription = ""
                                )
                            },
                            actions = {
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(painterResource(id = R.drawable.ic_baseline_more_vert_24), contentDescription = "Menu")
                                }
                            }
                        )
                    },
                    bottomBar = {
//                        Navigation()
                    },
                    ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            Column {
                                val activities = chatViewModel.botResponse.value
                                CreateMessageBubbles(activities = activities, chatViewModel::sendUserInput)
                            }
                            MessageInput(chatViewModel::sendUserInput)
                        }


                    }
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview2() {

    VitalisTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            "Chat",
                            color = MaterialTheme.colorScheme.primary,
                        )
                    },
                    Modifier.background(color = MaterialTheme.colorScheme.tertiary),
                    navigationIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
                            contentDescription = ""
                        )
                    },
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(painterResource(id = R.drawable.ic_baseline_more_vert_24), contentDescription = "Menu")
                        }
                    }
                )
            },
            bottomBar = {
//                Navigation()
            },
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {

                }

            }
        }
    }
}
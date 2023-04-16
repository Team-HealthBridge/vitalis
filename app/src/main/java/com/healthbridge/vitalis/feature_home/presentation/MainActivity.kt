package com.healthbridge.vitalis.feature_home.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.healthbridge.vitalis.R
import com.healthbridge.vitalis.commons.components.Navigation
import com.healthbridge.vitalis.feature_auth.ui.AuthActivity
import com.healthbridge.vitalis.feature_auth.ui.AuthRepository
import com.healthbridge.vitalis.feature_auth.ui.AuthViewModel
import com.healthbridge.vitalis.feature_bot.presentation.ChatScreen
import com.healthbridge.vitalis.feature_home.data.repository.HealthBitsRepository
import com.healthbridge.vitalis.feature_home.presentation.components.HealthBits
import com.healthbridge.vitalis.feature_home.presentation.components.InformationCard
import com.healthbridge.vitalis.feature_home.presentation.viewmodels.HealthBitsViewModel
import com.healthbridge.vitalis.ui.theme.VitalisTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        val context = this

        val authRepository = AuthRepository()
        val viewModel = AuthViewModel(authRepository)

        val user = viewModel.currentUser

        val healthBitsRepository = HealthBitsRepository()
        val healthBitsViewModel = HealthBitsViewModel(healthBitsRepository)

        super.onCreate(savedInstanceState)
        setContent {
            VitalisTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    "HELLO ${user?.displayName?.uppercase()}",
                                    color = MaterialTheme.colorScheme.primary,
                                )
                            },
                            Modifier.background(color = MaterialTheme.colorScheme.tertiary),
                            navigationIcon = {
                                val purple = MaterialTheme.colorScheme.primaryContainer
                                Text(
                                    text = "${user?.displayName?.first()}",
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .drawBehind {
                                            drawCircle(
                                                color = purple,
                                                radius = this.size.maxDimension
                                            )
                                        },
                                )
                            },
                            actions = {
                                val expanded = remember { mutableStateOf(false) }
                                IconButton(onClick = { expanded.value = true }) {
                                    Icon(
                                        painterResource(id = R.drawable.ic_baseline_menu_24),
                                        contentDescription = "Menu"
                                    )
                                }
                                DropdownMenu(
                                    expanded = expanded.value,
                                    onDismissRequest = { expanded.value = false },
                                ) {
                                    DropdownMenuItem(
                                        onClick = {
                                            viewModel.firebaseSignOut()
                                            val intent = Intent(context, AuthActivity::class.java)
                                            startActivity(intent)

                                        },
                                        text = { Text(text = "Log Out") },
                                        leadingIcon = {
                                            Icon(
                                                painter = painterResource(id = R.drawable.ic_baseline_arrow_outward_24),
                                                contentDescription = null
                                            )
                                        }
                                    )

                                }
                            }
                        )
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                startActivity(Intent(this, ChatScreen::class.java))
                            },
                            shape = CircleShape, // this makes the button round

                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.chatwidget),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(50.dp)
                            )
                        }
                    },
                    bottomBar = {
                        Navigation()
                    },

                    ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        val event = healthBitsViewModel.event.value
                        InformationCard(
                            image = event.pictureUrl,
                            title = event.title,
                            description = event.description,
                            url = event.learnMoreUrl,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Health Bits",
                                modifier = Modifier.padding(10.dp),
                                style = MaterialTheme.typography.headlineSmall
                            )
                            OutlinedButton(
                                onClick = {
                                          val intent = Intent(context, MoreHealthBitsScreen::class.java)
                                            context.startActivity(intent)
                                },
                                modifier = Modifier.align(Alignment.CenterVertically).padding(end = 10.dp),
                            ) {
                                Text(text = "See More")
                            }
                        }
                        LazyVerticalStaggeredGrid(
                            columns = StaggeredGridCells.Fixed(2),
                            contentPadding = PaddingValues(10.dp), // this adds padding around the whole grid
                            modifier = Modifier.fillMaxHeight(0.9f)
                        ) {
                            healthBitsViewModel.healthBits.value.forEach {
                                item {
                                    Box(modifier = Modifier.padding(10.dp)) { // this adds padding around each item
                                        HealthBits(
                                            image = it.pictureUrl,
                                            title = it.category,
                                            description = it.description
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}



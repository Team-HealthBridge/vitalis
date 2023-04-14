package com.healthbridge.vitalis.feature_home.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.healthbridge.vitalis.R
import com.healthbridge.vitalis.commons.components.Navigation
import com.healthbridge.vitalis.feature_auth.ui.AuthActivity
import com.healthbridge.vitalis.feature_auth.ui.AuthRepository
import com.healthbridge.vitalis.feature_auth.ui.AuthViewModel
import com.healthbridge.vitalis.feature_bot.presentation.ChatScreen
import com.healthbridge.vitalis.feature_home.presentation.components.HealthBits
import com.healthbridge.vitalis.feature_home.presentation.components.InformationCard
import com.healthbridge.vitalis.ui.theme.VitalisTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        val context = this

        val authRepository = AuthRepository()
        val viewModel = AuthViewModel(authRepository)

        super.onCreate(savedInstanceState)
        setContent {
            VitalisTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    "HELLO ILHAN",
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
                                val expanded = remember { mutableStateOf(false) }
                                IconButton(onClick = { expanded.value = true }) {
                                    Icon(
                                        painterResource(id = R.drawable.ic_baseline_menu_24),
                                        contentDescription = "Menu"
                                    )
                                }
                                DropdownMenu(
                                    expanded = expanded.value ,
                                    onDismissRequest = { expanded.value = false },
                                ){
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

                    ) {

                    Column {
                        Column(
                            modifier = Modifier
                                .paddingFromBaseline(top = 100.dp)
                                .padding(horizontal = 20.dp)
                        ) {
                            InformationCard(modifier = Modifier.align(Alignment.CenterHorizontally))
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
                                    onClick = { /*TODO*/ },
                                    modifier = Modifier.align(Alignment.CenterVertically),
                                ) {
                                    Text(text = "See More")
                                }
                            }
                        }
                        Column {
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                contentPadding = PaddingValues(10.dp), // this adds padding around the whole grid
                                modifier = Modifier.fillMaxHeight(0.9f)
                            ) {
                                items(10) {
                                    Box(modifier = Modifier.padding(10.dp)) { // this adds padding around each item
                                        HealthBits(
                                            image = "https://images.unsplash.com/photo-1604480132736-44c188fe4d20?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2790&q=80",
                                            title = "Mental Health",
                                            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
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

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        VitalisTheme {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                "HELLO ILHAN",
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
                                Icon(
                                    painterResource(id = R.drawable.ic_baseline_menu_24),
                                    contentDescription = "Menu"
                                )
                            }
                        }
                    )
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { /* fab click handler */ },
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

                ) {

                Column {
                    Column(
                        modifier = Modifier
                            .paddingFromBaseline(top = 100.dp)
                            .padding(horizontal = 20.dp)
                    ) {
                        InformationCard(modifier = Modifier.align(Alignment.CenterHorizontally))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Health Bits",
                                modifier = Modifier.padding(20.dp),
                                style = MaterialTheme.typography.headlineSmall
                            )
                            OutlinedButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier.align(Alignment.CenterVertically),
                            ) {
                                Text(text = "See More")
                            }
                        }
                    }
                    Column {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            contentPadding = PaddingValues(10.dp), // this adds padding around the whole grid
                            modifier = Modifier.fillMaxHeight(0.9f)
                        ) {
                            items(10) {
                                Box(modifier = Modifier.padding(10.dp)) { // this adds padding around each item
                                    HealthBits(
                                        image = "https://images.unsplash.com/photo-1604480132736-44c188fe4d20?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2790&q=80",
                                        title = "Mental Health",
                                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
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

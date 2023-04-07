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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.healthbridge.vitalis.R
import com.healthbridge.vitalis.commons.components.Navigation
import com.healthbridge.vitalis.feature_bot.presentation.ChatScreen
import com.healthbridge.vitalis.feature_home.presentation.components.HealthBits
import com.healthbridge.vitalis.feature_home.presentation.components.InformationCard
import com.healthbridge.vitalis.ui.theme.VitalisTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
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
                                        HealthBits()
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
                                    HealthBits()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

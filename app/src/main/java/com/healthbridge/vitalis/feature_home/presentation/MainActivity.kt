package com.healthbridge.vitalis.feature_home.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.relay.compose.BoxScopeInstanceImpl.align
import com.healthbridge.vitalis.R
import com.healthbridge.vitalis.commons.components.Navigation
import com.healthbridge.vitalis.feature_home.presentation.components.HealthBits
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
                                    Icon(painterResource(id = R.drawable.ic_baseline_menu_24), contentDescription = "Menu")
                                }
                            }
                        )
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { /* fab click handler */ }
                        ) {
                            Image(painter = painterResource(id = R.drawable.ic_launcher), contentDescription = "" )
                        }
                    },

                    ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                    ){
                        items(10) {
                            HealthBits()
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
                            Icon(painterResource(id = R.drawable.ic_baseline_menu_24), contentDescription = "Menu")
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { /* fab click handler */ }
                ) {
                    Image(painter = painterResource(id = R.drawable.chatwidget), contentDescription = "" )
                }
            },

        ) {
            Column {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxHeight(0.8f)
                ) {
                    items(10) {
                        HealthBits()
                    }
                }
                Navigation()
            }
        }
    }
}

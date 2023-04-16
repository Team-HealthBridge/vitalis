package com.healthbridge.vitalis.feature_records.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.healthbridge.vitalis.R
import com.healthbridge.vitalis.commons.components.Navigation
import com.healthbridge.vitalis.feature_records.presentation.components.SectionCard
import com.healthbridge.vitalis.ui.theme.VitalisTheme


class RecordsScreen : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            val context = LocalContext.current
            VitalisTheme {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                androidx.compose.material3.Text(
                                    "Health Center",
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
                    bottomBar = {
                        Navigation()
                    },
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        ElevatedCard(
                            onClick = { /* Do something */ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                                .height(150.dp)
                        ) {
                            Row {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data("https://images.unsplash.com/photo-1517607648415-b431854daa86?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=3434&q=80")
                                        .crossfade(true)
                                        .build(),
                                    placeholder = painterResource(id = R.drawable.coming_soon),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxWidth(0.3f)
                                        .height(150.dp)
                                )
                                Column(
                                    modifier = Modifier.padding(8.dp)
                                ) {
                                    Text(
                                        text = "Health Center",
                                        style = MaterialTheme.typography.titleSmall
                                    )
                                    Spacer(modifier = Modifier.height(20.dp))
                                    Text(
                                        text = "All your medical information, records and much more, all in one place",
                                        style = MaterialTheme.typography.labelSmall
                                    )

                                }

                            }


                        }

                        SectionCard(
                            image = R.drawable.measurements,
                            title = "Body Measurements",
                            description = "All your body measurements in one place",
                            onClick = {
                                val intent = Intent(context, MeasurementsScreen::class.java)
                                context.startActivity(intent)
                            }
                        )

                        SectionCard(
                            image = R.drawable.vitals,
                            title = "Vitals",
                            description = "All your vital signs in one place",
                            onClick = {
                                val intent = Intent(context, VitalsScreen::class.java)
                                context.startActivity(intent)
                            })

                        SectionCard(
                            image = R.drawable.records,
                            title = "Medical History",
                            description = "All your medical history in one place",
                            onClick = {
                                val intent = Intent(context, HealthRecordsScreen::class.java)
                                context.startActivity(intent)
                            })
                    }
                }

            }
        }
    }

}
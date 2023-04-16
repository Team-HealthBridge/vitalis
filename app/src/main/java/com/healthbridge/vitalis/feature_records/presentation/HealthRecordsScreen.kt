package com.healthbridge.vitalis.feature_records.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.healthbridge.vitalis.R
import com.healthbridge.vitalis.commons.components.Navigation
import com.healthbridge.vitalis.feature_records.presentation.viewmodels.RecordsViewModel
import com.healthbridge.vitalis.feature_records.presentation.viewmodels.RecordsViewModelProvider
import com.healthbridge.vitalis.ui.theme.VitalisTheme

class HealthRecordsScreen: ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            VitalisTheme {
                val viewModel: RecordsViewModel =
                    viewModel(factory = RecordsViewModelProvider.Factory)
                val context = LocalContext.current

                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(
                                    "Health Records",
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
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {

                        viewModel.getHealthRecords()

                        if (viewModel.healthRecords.value.isEmpty()) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Text(
                                    "No health records added yet.",
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.padding(16.dp),
                                    style = MaterialTheme.typography.headlineSmall
                                )
                                Button(onClick = {
                                    val intent = Intent(context, NewHealthRecordsScreen::class.java)
                                    context.startActivity(intent)
                                }) {
                                    Text("Add Record")
                                }

                            }
                        } else {
                            LazyColumn {
                                val size = viewModel.healthRecords.value.size
                                items(size) { index ->
                                    viewModel.healthRecords.value[index].let { healthRecord ->
                                        ElevatedCard(
                                            modifier = Modifier
                                                .padding(20.dp)
                                                .fillMaxWidth()

                                        ) {
                                            Column() {

                                                val date =
                                                    healthRecord.createdDate.year.toString() + "-" + healthRecord.createdDate.monthValue.toString() + "-" + healthRecord.createdDate.dayOfMonth.toString()

                                                Text(
                                                    "Date: $date",
                                                    color = MaterialTheme.colorScheme.primary,
                                                    modifier = Modifier.padding(16.dp),
                                                    style = MaterialTheme.typography.headlineSmall
                                                )

                                                Text(
                                                    "Diagnosis: ${healthRecord.diagnosis}",
                                                    modifier = Modifier.padding(16.dp),
                                                    style = MaterialTheme.typography.bodySmall
                                                )

                                                Text(
                                                    "Chief Complaint: ${healthRecord.chiefComplaint}",
                                                    modifier = Modifier.padding(16.dp),
                                                    style = MaterialTheme.typography.bodySmall
                                                )

                                                Text(
                                                    "Treatment: ${healthRecord.treatmentPlan}",
                                                    modifier = Modifier.padding(16.dp),
                                                    style = MaterialTheme.typography.bodySmall
                                                )


                                            }

                                        }


                                    }
                                }

                                item {
                                    Button(
                                        onClick = {
                                        val intent = Intent(context, NewVitalsScreen::class.java)
                                        context.startActivity(intent) },
                                        modifier = Modifier
                                            .padding(60.dp)
                                            .fillMaxWidth()
                                    ) {
                                        Text("Record New Health Record")
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
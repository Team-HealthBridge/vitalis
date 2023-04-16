package com.healthbridge.vitalis.feature_records.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.healthbridge.vitalis.R
import com.healthbridge.vitalis.commons.components.Navigation
import com.healthbridge.vitalis.feature_records.data.entities.HealthRecord
import com.healthbridge.vitalis.feature_records.presentation.viewmodels.RecordsViewModel
import com.healthbridge.vitalis.feature_records.presentation.viewmodels.RecordsViewModelProvider
import com.healthbridge.vitalis.ui.theme.VitalisTheme

class NewHealthRecordsScreen: ComponentActivity() {

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
                                    "Add New Health Record",
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

                ) {
                    val viewModel: RecordsViewModel = viewModel(factory = RecordsViewModelProvider.Factory)

                    val context = LocalContext.current

                    val hospitalName =  remember { mutableStateOf("") }
                    val chiefComplaint =  remember { mutableStateOf("") }
                    val historyOfPresentIllness =  remember { mutableStateOf("") }
                    val pastMedicalHistory =  remember { mutableStateOf("") }
                    val familyHistory =  remember { mutableStateOf("") }
                    val socialHistory =  remember { mutableStateOf("") }
                    val physicalExam = remember { mutableStateOf("") }
                    val diagnosis = remember { mutableStateOf("") }
                    val treatmentPlan = remember { mutableStateOf("") }
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = MaterialTheme.colorScheme.background)
                            .padding(it)
                    ) {


                        item {

                            OutlinedTextField(
                                value = hospitalName.value,
                                onValueChange = { hospitalName.value = it },
                                label = { Text("Hospital Name") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 40.dp),
                                placeholder = { Text("Hospital Name") }
                            )
                        }

                        item{
                            OutlinedTextField(
                                value = chiefComplaint.value,
                                onValueChange = { chiefComplaint.value = it },
                                label = { Text("Chief Complaint") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 40.dp),

                                )
                        }

                        item{
                            OutlinedTextField(
                                value = historyOfPresentIllness.value,
                                onValueChange = { historyOfPresentIllness.value = it },
                                label = { Text("History of Present Illness") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 40.dp),

                                )
                        }

                        item{
                            OutlinedTextField(
                                value = pastMedicalHistory.value,
                                onValueChange = { pastMedicalHistory.value = it },
                                label = { Text("Past Medical History") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 40.dp),
                            )
                        }

                        item{
                            OutlinedTextField(
                                value = familyHistory.value,
                                onValueChange = { familyHistory.value = it },
                                label = { Text("Family History") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 40.dp),
                            )
                        }

                        item{
                            OutlinedTextField(
                                value = socialHistory.value,
                                onValueChange = { socialHistory.value = it },
                                label = { Text("Social History") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 40.dp),
                            )
                        }

                        item{
                            OutlinedTextField(
                                value = physicalExam.value,
                                onValueChange = { physicalExam.value = it },
                                label = { Text("Physical Exam") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 40.dp),
                            )
                        }

                        item{
                            OutlinedTextField(
                                value = diagnosis.value,
                                onValueChange = { diagnosis.value = it },
                                label = { Text("Diagnosis") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 40.dp),
                            )
                        }

                        item{
                            OutlinedTextField(
                                value = treatmentPlan.value,
                                onValueChange = { treatmentPlan.value = it },
                                label = { Text("Treatment Plan") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 40.dp),
                            )
                        }

                        item{
                            Button(
                                onClick = {
                                    val healthRecord = HealthRecord(
                                        hospitalName = hospitalName.value,
                                        chiefComplaint = chiefComplaint.value,
                                        historyOfPresentIllness = historyOfPresentIllness.value,
                                        pastMedicalHistory = pastMedicalHistory.value,
                                        familyHistory = familyHistory.value,
                                        socialHistory = socialHistory.value,
                                        physicalExam = physicalExam.value,
                                        diagnosis = diagnosis.value,
                                        treatmentPlan = treatmentPlan.value
                                    )
                                    viewModel.insertHealthRecord(healthRecord)
                                    Toast.makeText(context, "Health Record Added", Toast.LENGTH_SHORT).show()
                                    val intent = Intent(context, HealthRecordsScreen::class.java)
                                    context.startActivity(intent)
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 40.dp, vertical = 20.dp)
                            ) {
                                Text("Add New Health Record")
                            }
                        }

                    }
                }
            }
        }
    }
}
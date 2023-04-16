package com.healthbridge.vitalis.feature_records.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.healthbridge.vitalis.R
import com.healthbridge.vitalis.commons.components.Navigation
import com.healthbridge.vitalis.feature_records.data.entities.Vitals
import com.healthbridge.vitalis.feature_records.presentation.viewmodels.RecordsViewModel
import com.healthbridge.vitalis.feature_records.presentation.viewmodels.RecordsViewModelProvider
import com.healthbridge.vitalis.ui.theme.VitalisTheme

class NewVitalsScreen: ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            VitalisTheme {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(
                                    "Add New Vitals",
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

                    val bloodPressure = remember { mutableStateOf("")}
                    val glucose = remember { mutableStateOf("")}
                    val heartRate = remember { mutableStateOf("")}
                    val respiratoryRate = remember { mutableStateOf("")}
                    val temperature = remember { mutableStateOf("")}
                    val oxygenSaturation = remember { mutableStateOf("")}
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                    ){

                        TextField(
                            value = bloodPressure.value,
                            onValueChange = { bloodPressure.value = it },
                            label = { Text("Blood Pressure") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 40.dp),
                            placeholder = { Text("120/90") }
                        )

                        TextField(
                            value = glucose.value,
                            onValueChange = { glucose.value = it },
                            label = { Text("Glucose Level") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 40.dp),
                            placeholder = { Text("5.7") }
                        )

                        TextField(
                            value = heartRate.value,
                            onValueChange = { heartRate.value = it },
                            label = { Text("Heart Rate") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 40.dp),
                            placeholder = { Text("80") }
                        )

                        TextField(
                            value = respiratoryRate.value,
                            onValueChange = { respiratoryRate.value = it },
                            label = { Text("Respiratory Rate") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 40.dp),
                            placeholder = { Text("12") }
                        )

                        TextField(
                            value = temperature.value,
                            onValueChange = { temperature.value = it },
                            label = { Text("Temperature") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 40.dp),
                            placeholder = { Text("37.5") }
                        )

                        TextField(
                            value = oxygenSaturation.value,
                            onValueChange = { oxygenSaturation.value = it },
                            label = { Text("Oxygen Saturation") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 40.dp),
                            placeholder = { Text("98") }
                        )

                        Button(onClick = {
                            val newVitals = Vitals(
                                bloodPressure = bloodPressure.value,
                                glucose = glucose.value,
                                heartRate = heartRate.value,
                                respiratoryRate = respiratoryRate.value,
                                temperature = temperature.value,
                                oxygenSaturation = oxygenSaturation.value
                            )
                            viewModel.insertVitals(newVitals)

                        }) {
                            Text(text = "Save")
                        }
                    }

                }

            }
        }
    }
}
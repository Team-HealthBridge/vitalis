package com.healthbridge.vitalis.feature_records.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.healthbridge.vitalis.R
import com.healthbridge.vitalis.commons.components.Navigation
import com.healthbridge.vitalis.feature_records.data.entities.Measurement
import com.healthbridge.vitalis.feature_records.presentation.viewmodels.RecordsViewModel
import com.healthbridge.vitalis.feature_records.presentation.viewmodels.RecordsViewModelProvider
import com.healthbridge.vitalis.ui.theme.VitalisTheme
import kotlin.math.pow
import kotlin.math.round

class MeasurementsScreen(): ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContent { 
            VitalisTheme {
                val viewModel: RecordsViewModel = viewModel(factory = RecordsViewModelProvider.Factory)

                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(
                                    "Body Measurements",
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

                        val height: MutableState<Double> = remember {
                            mutableStateOf(0.0)
                        }
                        val weight: MutableState<Double> = remember {
                            mutableStateOf(0.0)
                        }
                        val bmi: MutableState<Double> = remember {
                            mutableStateOf(0.0)
                        }

                        viewModel.getMeasurements()

                        if(viewModel.measurements?.value?.isNotEmpty() == true){
                            val measurement = viewModel.measurements?.value?.last()
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(40.dp),
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(40.dp)
                                ) {
                                    Text(text = "Last Measurement", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.primary)
                                    Text(text = "Height: ${measurement?.height}")
                                    Text(text = "Weight: ${measurement?.weight}")
                                    Text(text = "BMI: ${measurement?.bmi}")
                                }
                            }
                        }


                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(40.dp)
                        ){
                            OutlinedTextField(
                                label = { Text(text = "Height in metres") },
                                value = height.value.toString(),
                                onValueChange = {
                                    height.value = it.toDouble()
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )

                            bmi.value = calculateBMI(height, weight)

                            OutlinedTextField(
                                label = { Text(text = "Weight in kg") },
                                value = weight.value.toString(),
                                onValueChange = {
                                    weight.value = it.toDouble()
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )

                            Button(
                                onClick = {
                                    val measurement = Measurement(
                                        height = height.value,
                                        weight = weight.value,
                                        bmi = bmi.value
                                    )
                                    viewModel.insertMeasurement(measurement)
                                }
                            ) {
                                if(viewModel.measurements?.value?.isNotEmpty() == true){
                                    Text(text = "Update")
                                }else{
                                    Text(text = "Save")
                                }
                            }
                        }


                    }

                }


                
            }
        }
    }

    private fun calculateBMI(height: MutableState<Double>, weight: MutableState<Double>): Double {
        return (weight.value / (height.value * height.value)).roundTo(2)
    }
    fun Double.roundTo(numDecimalPlaces: Int): Double {
        val factor = 10.0.pow(numDecimalPlaces)
        return round(this * factor) / factor
    }
}
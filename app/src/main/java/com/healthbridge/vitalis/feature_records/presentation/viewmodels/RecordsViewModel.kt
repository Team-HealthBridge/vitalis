package com.healthbridge.vitalis.feature_records.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.healthbridge.vitalis.feature_records.data.entities.HealthRecord
import com.healthbridge.vitalis.feature_records.data.entities.Measurement
import com.healthbridge.vitalis.feature_records.data.entities.Vitals
import com.healthbridge.vitalis.feature_records.data.repository.HealthRecordRepository
import com.healthbridge.vitalis.feature_records.data.repository.MeasurementRepository
import com.healthbridge.vitalis.feature_records.data.repository.VitalsRepository
import kotlinx.coroutines.launch

// View Model to validate and insert data into the room database

class RecordsViewModel(
    private val vitalsRepository: VitalsRepository,
    private val healthRecordRepository: HealthRecordRepository,
    private val measurementRepository: MeasurementRepository
) : ViewModel() {

    val vitals : MutableState<List<Vitals>> = mutableStateOf(emptyList())
    val healthRecords: MutableState<List<HealthRecord>> = mutableStateOf(emptyList())
    var measurements: MutableState<List<Measurement>>? = mutableStateOf(emptyList())
    fun getMeasurements() {
        viewModelScope.launch {
            measurementRepository.getAllMeasurements().collect{

                measurements?.value = it
            }

        }
    }

    fun getVitals() {
        viewModelScope.launch {
            vitalsRepository.getAllVitals().collect{
                vitals.value = it
                println("Vitals Viewmodel: ${it.size}")
            }
        }
    }

    fun getHealthRecords() {
        viewModelScope.launch {
            healthRecordRepository.getAllHealthRecords().collect{
                healthRecords.value = it
            }
        }
    }

    fun getVitalsById(id: Int) = vitalsRepository.getVitalsById(id)
    fun getHealthRecordById(id: Int) = healthRecordRepository.getHealthRecordById(id)
    fun getMeasurementById(id: Int) = measurementRepository.getMeasurementById(id)

    fun insertVitals(vitals: Vitals) {
        viewModelScope.launch {
            vitalsRepository.insertVitals(vitals)
        }
    }

    fun updateVitals(vitals: Vitals) {
        viewModelScope.launch {
            vitalsRepository.updateVitals(vitals)
        }
    }

    fun deleteVitals(vitals: Vitals) {
        viewModelScope.launch {
            vitalsRepository.deleteVitals(vitals)
        }
    }

    fun insertHealthRecord(healthRecord: HealthRecord) {
        viewModelScope.launch {
            healthRecordRepository.insertHealthRecord(healthRecord)
        }
    }

    fun updateHealthRecord(healthRecord: HealthRecord) {
        viewModelScope.launch {
            healthRecordRepository.updateHealthRecord(healthRecord)
        }
    }

    fun deleteHealthRecord(healthRecord: HealthRecord) {
        viewModelScope.launch {
            healthRecordRepository.deleteHealthRecord(healthRecord)
        }
    }

    fun insertMeasurement(measurement: Measurement) {
        viewModelScope.launch {
            measurementRepository.insertMeasurement(measurement)
        }
    }

    fun updateMeasurement(measurement: Measurement) {
        viewModelScope.launch {
            measurementRepository.updateMeasurement(measurement)
        }
    }

    fun deleteMeasurement(measurement: Measurement) {
        viewModelScope.launch {
            measurementRepository.deleteMeasurement(measurement)
        }
    }
}
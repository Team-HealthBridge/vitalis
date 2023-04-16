package com.healthbridge.vitalis.feature_records.data.repository

import com.healthbridge.vitalis.feature_records.data.entities.Measurement
import kotlinx.coroutines.flow.Flow

interface MeasurementRepository {

    fun getAllMeasurements(): Flow<List<Measurement>>

    fun getMeasurementById(id: Int): Flow<Measurement>

    suspend fun insertMeasurement(measurement: Measurement)

    suspend fun updateMeasurement(measurement: Measurement)

    suspend fun deleteMeasurement(measurement: Measurement)
}
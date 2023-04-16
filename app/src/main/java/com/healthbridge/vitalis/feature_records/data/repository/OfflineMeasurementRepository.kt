package com.healthbridge.vitalis.feature_records.data.repository

import com.healthbridge.vitalis.feature_records.data.entities.Measurement
import com.healthbridge.vitalis.feature_records.data.interfaces.MeasurementDao
import kotlinx.coroutines.flow.Flow

class OfflineMeasurementRepository(private val measurementDao: MeasurementDao): MeasurementRepository {

    override fun getAllMeasurements(): Flow<List<Measurement>> = measurementDao.getAllMeasurements()

    override fun getMeasurementById(id: Int): Flow<Measurement> = measurementDao.getMeasurementById(id)

    override suspend fun insertMeasurement(measurement: Measurement) = measurementDao.insertMeasurement(measurement)

    override suspend fun updateMeasurement(measurement: Measurement) = measurementDao.updateMeasurement(measurement)

    override suspend fun deleteMeasurement(measurement: Measurement) = measurementDao.deleteMeasurement(measurement)
}
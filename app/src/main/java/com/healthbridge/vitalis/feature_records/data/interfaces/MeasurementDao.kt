package com.healthbridge.vitalis.feature_records.data.interfaces

import androidx.room.*
import com.healthbridge.vitalis.feature_records.data.entities.Measurement
import kotlinx.coroutines.flow.Flow


@Dao
interface MeasurementDao {

    @Insert
    suspend fun insertMeasurement(measurement: Measurement)

    @Update
    suspend fun updateMeasurement(measurement: Measurement)

    @Delete
    suspend fun deleteMeasurement(measurement: Measurement)

    @Query("SELECT * FROM measurements")
    fun getAllMeasurements(): Flow<List<Measurement>>

    @Query("SELECT * FROM measurements WHERE id = :id")
    fun getMeasurementById(id: Int): Flow<Measurement>
}
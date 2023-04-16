package com.healthbridge.vitalis.feature_records.data.interfaces

import androidx.room.*
import com.healthbridge.vitalis.feature_records.data.entities.HealthRecord
import kotlinx.coroutines.flow.Flow


@Dao
interface HealthRecordDao {

    @Insert
    suspend fun insertHealthRecord(healthRecord: HealthRecord)

    @Update
    suspend fun updateHealthRecord(healthRecord: HealthRecord)

    @Delete
    suspend fun deleteHealthRecord(healthRecord: HealthRecord)

    @Query("SELECT * FROM health_records")
    fun getAllHealthRecords(): Flow<List<HealthRecord>>

    @Query("SELECT * FROM health_records WHERE id = :id")
    fun getHealthRecordById(id: Int): Flow<HealthRecord>
}
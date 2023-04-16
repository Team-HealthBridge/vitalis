package com.healthbridge.vitalis.feature_records.data.repository

import com.healthbridge.vitalis.feature_records.data.entities.HealthRecord
import kotlinx.coroutines.flow.Flow

interface HealthRecordRepository {

    fun getAllHealthRecords(): Flow<List<HealthRecord>>

    fun getHealthRecordById(id: Int): Flow<HealthRecord>

    suspend fun insertHealthRecord(healthRecord: HealthRecord)

    suspend fun updateHealthRecord(healthRecord: HealthRecord)

    suspend fun deleteHealthRecord(healthRecord: HealthRecord)
}
package com.healthbridge.vitalis.feature_records.data.repository

import com.healthbridge.vitalis.feature_records.data.entities.HealthRecord
import com.healthbridge.vitalis.feature_records.data.interfaces.HealthRecordDao
import kotlinx.coroutines.flow.Flow

class OfflineHealthRecordRepository(private val healthRecordDao: HealthRecordDao ) : HealthRecordRepository {
    override fun getAllHealthRecords(): Flow<List<HealthRecord>> = healthRecordDao.getAllHealthRecords()

    override fun getHealthRecordById(id: Int): Flow<HealthRecord> = healthRecordDao.getHealthRecordById(id)

    override suspend fun insertHealthRecord(healthRecord: HealthRecord) = healthRecordDao.insertHealthRecord(healthRecord)

    override suspend fun updateHealthRecord(healthRecord: HealthRecord) = healthRecordDao.updateHealthRecord(healthRecord)

    override suspend fun deleteHealthRecord(healthRecord: HealthRecord) = healthRecordDao.deleteHealthRecord(healthRecord)
}
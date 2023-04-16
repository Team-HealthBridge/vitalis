package com.healthbridge.vitalis.feature_records.data.repository

import com.healthbridge.vitalis.feature_records.data.entities.Vitals
import com.healthbridge.vitalis.feature_records.data.interfaces.VitalsDao
import kotlinx.coroutines.flow.Flow

class OfflineVitalsRepository(private val vitalsDao: VitalsDao) : VitalsRepository{
    override fun getAllVitals(): Flow<List<Vitals>> = vitalsDao.getAllVitals()

    override fun getVitalsById(id: Int): Flow<Vitals> = vitalsDao.getVitalsById(id)

    override suspend fun insertVitals(vitals: Vitals) = vitalsDao.insertVitals(vitals)

    override suspend fun updateVitals(vitals: Vitals) = vitalsDao.updateVitals(vitals)

    override suspend fun deleteVitals(vitals: Vitals) = vitalsDao.deleteVitals(vitals)
}
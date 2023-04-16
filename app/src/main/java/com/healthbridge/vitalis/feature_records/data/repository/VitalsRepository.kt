package com.healthbridge.vitalis.feature_records.data.repository

import com.healthbridge.vitalis.feature_records.data.entities.Vitals
import kotlinx.coroutines.flow.Flow

interface VitalsRepository {

    fun getAllVitals(): Flow<List<Vitals>>

    fun getVitalsById(id: Int): Flow<Vitals>

    suspend fun insertVitals(vitals: Vitals)

    suspend fun updateVitals(vitals: Vitals)

    suspend fun deleteVitals(vitals: Vitals)
}
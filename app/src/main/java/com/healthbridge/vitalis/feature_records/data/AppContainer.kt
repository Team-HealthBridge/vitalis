package com.healthbridge.vitalis.feature_records.data

import android.content.Context
import com.healthbridge.vitalis.feature_records.data.repository.*

interface AppContainer {
    val healthRecordRepository: HealthRecordRepository
    val vitalsRepository: VitalsRepository
    val measurementRepository: MeasurementRepository
}


class AppContainerImpl(private val context: Context) : AppContainer {
    override val healthRecordRepository: HealthRecordRepository by lazy { OfflineHealthRecordRepository(RecordsDatabase.getDatabase(context).healthRecordDao()) }
    override val vitalsRepository: VitalsRepository by lazy { OfflineVitalsRepository(RecordsDatabase.getDatabase(context).vitalsDao()) }
    override val measurementRepository: MeasurementRepository by lazy { OfflineMeasurementRepository(RecordsDatabase.getDatabase(context).measurementDao()) }
}
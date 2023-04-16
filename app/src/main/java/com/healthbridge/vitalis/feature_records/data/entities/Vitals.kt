package com.healthbridge.vitalis.feature_records.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "vitals")
data class Vitals (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val bloodPressure: String,
    val glucose: String,
    val heartRate: String,
    val respiratoryRate: String,
    val temperature: String,
    val oxygenSaturation: String,
    val createdDate: LocalDateTime = LocalDateTime.now()
)
package com.healthbridge.vitalis.feature_records.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "health_records")
class HealthRecord (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val hospitalName: String,
    val chiefComplaint: String,
    val historyOfPresentIllness: String,
    val pastMedicalHistory: String,
    val familyHistory: String,
    val socialHistory: String,
    val physicalExam: String,
    val diagnosis: String,
    val treatmentPlan: String,
    val createdDate: LocalDateTime = LocalDateTime.now()

        )
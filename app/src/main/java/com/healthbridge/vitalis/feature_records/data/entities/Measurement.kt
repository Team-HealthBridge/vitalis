package com.healthbridge.vitalis.feature_records.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.time.LocalDateTime

@Entity(tableName = "measurements")
data class Measurement (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val height: Double,
    val weight: Double,
    val bmi: Double,
    val createdDate: LocalDateTime = LocalDateTime.now()

)

class LocalDateTimeConverter {
    @TypeConverter
    fun fromLocalDateTime(localDateTime: LocalDateTime?): String? {
        return localDateTime?.toString()
    }

    @TypeConverter
    fun toLocalDateTime(dateTimeString: String?): LocalDateTime? {
        return dateTimeString?.let { LocalDateTime.parse(it) }
    }
}

package com.healthbridge.vitalis.feature_records.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.healthbridge.vitalis.feature_records.data.entities.HealthRecord
import com.healthbridge.vitalis.feature_records.data.entities.LocalDateTimeConverter
import com.healthbridge.vitalis.feature_records.data.entities.Measurement
import com.healthbridge.vitalis.feature_records.data.entities.Vitals
import com.healthbridge.vitalis.feature_records.data.interfaces.HealthRecordDao
import com.healthbridge.vitalis.feature_records.data.interfaces.MeasurementDao
import com.healthbridge.vitalis.feature_records.data.interfaces.VitalsDao

@Database(entities = [Measurement::class, HealthRecord::class, Vitals::class], version = 4)
@TypeConverters(
    LocalDateTimeConverter::class)
abstract class RecordsDatabase: RoomDatabase() {

    abstract fun measurementDao(): MeasurementDao

    abstract fun healthRecordDao(): HealthRecordDao

    abstract fun vitalsDao(): VitalsDao


    companion object {
        @Volatile
        private var INSTANCE: RecordsDatabase? = null

        fun getDatabase(context: Context): RecordsDatabase{
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context, RecordsDatabase::class.java, "records_database")
                    // Setting this option in your app's database builder means that Room
                    // permanently deletes all data from the tables in your database when it
                    // attempts to perform a migration with no defined migration path.
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE= it }
            }
        }
    }
}
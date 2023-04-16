package com.healthbridge.vitalis.feature_records.data.interfaces

import androidx.room.*
import com.healthbridge.vitalis.feature_records.data.entities.Vitals
import kotlinx.coroutines.flow.Flow

@Dao
interface VitalsDao {

    @Insert
    suspend fun insertVitals(vitals: Vitals)

    @Update
    suspend fun updateVitals(vitals: Vitals)

    @Delete
    suspend fun deleteVitals(vitals: Vitals)

    @Query("SELECT * FROM vitals")
    fun getAllVitals(): Flow<List<Vitals>>

    @Query("SELECT * FROM vitals WHERE id = :id")
    fun getVitalsById(id: Int): Flow<Vitals>

}
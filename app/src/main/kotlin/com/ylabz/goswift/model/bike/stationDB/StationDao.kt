package com.ylabz.goswift.model.bike.stationDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Do not use live data
 * import androidx.lifecycle.LiveData
 */
@Dao
interface StationDao {
    @Query("SELECT * FROM station")
    fun getAll(): Flow<List<Station>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(station: Station)

    /*
    @Query("SELECT * FROM station WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Station>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(station: Station)

    @Delete("DELETE FROM word_table")
    suspend fun delete(station: Station)*/
}
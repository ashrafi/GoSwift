package com.ylabz.goswift.model.togo.GoToEvtDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface GoToEvtDao {
    @Query("SELECT * FROM GoToEvent")
    fun getAll(): Flow<List<GoToEvt>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(togo: GoToEvt)

    @Query("DELETE FROM GoToEvent")
    suspend fun deleteAll()

    /*@Query("SELECT * FROM GoToEvent ORDER BY datetime(date)")
    suspend fun getByDate(): List<GoToEnvt>*/

    /*
    @Query("SELECT * FROM station WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<StationInfoDB>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(station: StationInfoDB)

    @Delete("DELETE FROM word_table")
    suspend fun delete(station: StationInfoDB)*/
}
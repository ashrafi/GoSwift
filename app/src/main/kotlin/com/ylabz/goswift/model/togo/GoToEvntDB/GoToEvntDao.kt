package com.ylabz.goswift.model.togo.GoToEvntDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface GoToEvntDao {
    @Query("SELECT * FROM GoToEvent")
    fun getAll(): Flow<List<GoToEnvt>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(togo: GoToEnvt)

    @Query("DELETE FROM GoToEvent")
    suspend fun deleteAll()

    /*
    @Query("SELECT * FROM station WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Station>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(station: Station)

    @Delete("DELETE FROM word_table")
    suspend fun delete(station: Station)*/
}
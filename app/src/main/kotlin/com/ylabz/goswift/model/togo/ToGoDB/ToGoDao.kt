package com.ylabz.goswift.model.togo.ToGoDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ToGoDao {
    @Query("SELECT * FROM ToGoEvent")
    fun getAll(): Flow<List<ToGo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(togo: ToGo)

    /*
    @Query("SELECT * FROM station WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Station>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(station: Station)

    @Delete("DELETE FROM word_table")
    suspend fun delete(station: Station)*/
}
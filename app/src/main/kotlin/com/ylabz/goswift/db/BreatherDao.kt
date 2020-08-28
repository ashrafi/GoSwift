package com.ylabz.goswift.db

import androidx.lifecycle.LiveData
import androidx.room.*


/**
 * more info:
 * https://developer.android.com/training/data-storage/room/accessing-data.html
 */
@Dao
interface BreatherDao {

    // @Query("SELECT * FROM breather_table WHERE l IN (:breatherIds)")
    // fun loadAllByIds(breatherIds: IntArray): List<Breather>

    @Query(
        "SELECT * FROM breather_table WHERE first_name LIKE :first AND " +
                "last_name LIKE :last LIMIT 1"
    )
    fun findByName(first: String, last: String): Breather

    @Query("SELECT * from breather_table ORDER BY last_name ASC")
    fun allLiveBreathers(): LiveData<List<Breather>>

    @Insert
    fun insertAll(vararg breather: Breather)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(breather: Breather)

    @Delete
    suspend fun delete(breather: Breather)

    @Query("DELETE FROM breather_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM breather_table")
    suspend fun getList(): List<Breather>

}
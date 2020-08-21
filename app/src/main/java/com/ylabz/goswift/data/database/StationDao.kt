package com.ylabz.goswift.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ylabz.goswift.data.database.model.Station

@Dao
interface StationDao {
    @Query("SELECT * FROM station")
    fun getAll(): LiveData<List<Station>>

    //@Query("SELECT * FROM station WHERE uid IN (:userIds)")
    //fun loadAllByIds(userIds: IntArray): List<Station>

    @Insert
    fun insert(station: Station)

    @Delete
    fun delete(station: Station)
}
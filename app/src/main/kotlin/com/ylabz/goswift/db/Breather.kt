package com.ylabz.goswift.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * https://developer.android.com/training/data-storage/room/defining-data.html
 */
@Entity(tableName = "breather_table")
data class Breather(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var breatherId: Long = 0,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?
)
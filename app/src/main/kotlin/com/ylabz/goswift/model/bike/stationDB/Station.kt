package com.ylabz.goswift.model.bike.stationDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "station")
data class Station(@ColumnInfo(name = "stationName") var stationName: String = "",
                   @ColumnInfo(name = "lat") var lat:Double,
                   @ColumnInfo(name = "lon") var lon: Double,
                   @ColumnInfo(name = "capacity") var capacity: Int,
                   @ColumnInfo(name = "image") var img:String = "lyft_bike") {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var stationId: Long = 0
}
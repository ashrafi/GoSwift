package com.ylabz.goswift.model.togo.ToGoDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ToGoEvent")
data class ToGo(@ColumnInfo(name = "eventName") var togoName: String = "",
                @ColumnInfo(name = "lat") var lat:Double,
                @ColumnInfo(name = "lon") var lon: Double,
                @ColumnInfo(name = "image") var img:String = "img") {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var togoId: Long = 0
}

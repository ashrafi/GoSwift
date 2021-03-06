package com.ylabz.goswift.model.togo.GoToEvtDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.OffsetDateTime

// Add add a camera to the event info
@Entity(tableName = "GoToEvent")
data class GoToEvt(
    @ColumnInfo(name = "eventName") var goToName: String = "",
    @ColumnInfo(name = "lat") var lat: Double,
    @ColumnInfo(name = "lon") var lon: Double,
    @ColumnInfo(name = "date") var date: OffsetDateTime,
    @ColumnInfo(name = "image") var img: String = "img"
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var gotoId: Long = 0
}

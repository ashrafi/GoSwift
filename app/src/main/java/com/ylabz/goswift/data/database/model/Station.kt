package com.ylabz.goswift.data.database.model

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

/**
 * Databases for the table.
 */
@Entity(tableName = "todo_task")
data class ToDoTask(@ColumnInfo(name = "completed_flag") val completed: Boolean = false,
                    @ColumnInfo(name = "task_description") val description: String) {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true) var id: Long = 0

}
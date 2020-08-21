package com.ylabz.goswift.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Databases for the table.
 */
@Entity(tableName = "todo_task")
data class ToDoTask(@ColumnInfo(name = "completed_flag") val completed: Boolean = false,
                @ColumnInfo(name = "task_description") val description: String) {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true) var id: Long = 0

}
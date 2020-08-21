package com.ylabz.goswift.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.ylabz.goswift.db.ToDoTask


@Dao
interface TaskDao {

    @Query("select * from todo_task")
    fun getAllTasks(): LiveData<List<ToDoTask>>

    @Query("select * from todo_task")
    fun getListOfTasks(): List<ToDoTask>

    @Query("SELECT * FROM todo_task")
    fun getAllPaged(): DataSource.Factory<Int, ToDoTask>

    //@Query("select * from task where id = id")
    //fun findTaskById(id: Long): ToDoTask

    @Insert(onConflict = REPLACE)
    fun insertTask(task: ToDoTask)

    @Update(onConflict = REPLACE)
    fun updateTask(task: ToDoTask)

    @Delete
    fun deleteTask(task: ToDoTask)

    @Query ("DELETE FROM todo_task")
    fun deleteAllTasks()

    @Query ("SELECT COUNT(*) FROM todo_task")
    fun getItemCount():Int

}
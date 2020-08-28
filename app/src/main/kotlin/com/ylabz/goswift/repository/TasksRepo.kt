package com.ylabz.goswift.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.ylabz.goswift.db.AppDatabase
import com.ylabz.goswift.db.ToDoTask


/**
 *
 * A Repository is a class that abstracts access to multiple data sources.
 *
 * Should touch both -> Model -> Room -> SQL
 * AND
 * Remote com.zoewave.breathezy.repository.com.zoewave.breathezy.repository.com.zoewave.breathezy.api.Data Source -> Retrofit -> WebService
 * AND
 * Remote FireBase DB
 *
 * Then sync it all
 *
 */
interface TasksRepo {
    fun giveTaskDB() : LiveData<List<ToDoTask>>
    fun deleteAllTasksDB()
    fun getAllPaged() : DataSource.Factory<Int, ToDoTask>
    fun addRandomTask()
    fun getNumberOfItems(): String?
    //fun testFBDB() : Task<QuerySnapshot>
}


class TasksRepoImpl(/*private val fireDB : FireStore,*/ androidContext: Context) : TasksRepo{

    var ItemCount = 0
    private val TAG = "Breather"

    var mDb : AppDatabase

    // get info from all network API (RESTFul calls)
    init {
        mDb = AppDatabase.getInstance(androidContext)
    }

    var context = androidContext

    /*override fun testFBDB() : Task<QuerySnapshot> {
        val data = fireDB.readFromFBDB()
        info("FireBase DB Called $data")
        return data
    }*/

    override fun giveTaskDB() : LiveData<List<ToDoTask>> {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return mDb.taskDao().getAllTasks()
    }


    //redditRepo = RedditData()

    override fun addRandomTask() {



        Thread {

            Log.v(TAG,"********** call reddit ********** ")
            //val stuff = redditRepo.callRetro(mDb)
            //randPic.callRandPic(mDb)
            //info(" title size of stuff  ${stuff.size}")



        }.start()
    }

    override fun deleteAllTasksDB() {
        Thread {
            mDb.taskDao().deleteAllTasks()
            ItemCount = mDb.taskDao().getItemCount()
            Log.v(TAG,"trying to delete all tasks $ItemCount")
        }.start()


    }

    override fun getNumberOfItems(): String? {

        return ItemCount.toString()
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAllPaged(): DataSource.Factory<Int, ToDoTask> {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return mDb.taskDao().getAllPaged()
    }

}
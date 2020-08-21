package com.ylabz.goswift.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.ylabz.goswift.db.ToDoTask
import com.ylabz.goswift.repository.TasksRepo



/**
 * Notice how it does not make a new network data source and instead gets the static singleton.
 * Shows NetworkDataSource.fetchData() running.
 *
 * When com.zoewave.breathezy.repository.com.zoewave.breathezy.repository.com.zoewave.breathezy.api.Data.intializeData() is called
 * onHandleIntent(), including when SyncIntentService provideDNetworkDataSource().
 *
 * Shows when Repository's observer is triggered and it puts the data into the database.
 *
 * Call Reddit and put it in the DB
 */

interface goswiftRepository {

    /**
     * Creates periodic sync tasks and checks to see if an immediate sync is required. If an
     * immediate sync is required, this method will take care of making sure that sync occurs.
     */
    fun initializeData()

    /**
     * Deletes old data because we don't need to keep old data
     */
    fun deleteOldData()

    /**
     * Checks if there are enough days of future weather for the app to display all the needed data.
     * @return Whether a fetch is needed
     */
    fun isFetchNeeded():Boolean

    /**
     *
     */
    //fun startFetchTasksService()

    fun getCurrentData(): MutableLiveData<Array<String>>?


    fun giveTaskDB() : LiveData<List<ToDoTask>>
    fun deleteAllTasksDB()
    fun getAllPaged() : DataSource.Factory<Int, ToDoTask>
    fun addRandomTask()
    fun getNumberOfItems(): String?
    //fun testFBDB() : Task<QuerySnapshot>


}

/**
 * Acts as a mediator between NetworkDataSource and com.zoewave.breathezy.repository.com.zoewave.breathezy.repository.com.zoewave.breathezy.api.Data Access Object (DAO)}
 */
class goswiftRepositoryImpl (private val tasksRepo: TasksRepo, /*private val fireDB: FireStore,*/ private val androidContext: Context): goswiftRepository {

    override fun addRandomTask() {
        tasksRepo.addRandomTask()
    }

    override fun getNumberOfItems(): String? {
       return tasksRepo.getNumberOfItems()
    }


    /*override fun testFBDB(): Task<QuerySnapshot> {
        return tasksRepo.testFBDB()
    }*/

    override fun getAllPaged(): DataSource.Factory<Int, ToDoTask> {
        return tasksRepo.getAllPaged()
    }

    override fun deleteAllTasksDB() {
        tasksRepo.deleteAllTasksDB()
    }

    override fun giveTaskDB(): LiveData<List<ToDoTask>> {
        return tasksRepo.giveTaskDB()
    }


    //LiveData storing the latest downloaded data
    private var mDownloadedTasks: MutableLiveData<Array<String>>? = null

    /*fun restaurants(@NonNull filters: Filters): QueryLiveData<Restaurant> {
        return QueryLiveData(toQuery(filters), Restaurant::class.java)
    }*/

    /*init {
        // As long as the repository exists, observe the network LiveData.
        // If that LiveData changes, update the database.
        val networkData = networkDataSource.getCurrentData()
        networkData!!.observeForever({ newDataFromNetwork ->
            /*mExecutors.diskIO().execute({
                // Deletes old historical data
                deleteOldData()
                Log.d(LOG_TAG, "Old weather deleted")
                // Insert our new weather data into Sunshine's database
                mWeatherDao.bulkInsert(newDataFromNetwork)
                Log.d(LOG_TAG, "New values inserted")
            })*/
            Log.d("from Repo", newDataFromNetwork.toString())
        })
    }*/

    private var mInitialized: Boolean = false

    override fun initializeData(){
        // Only perform initialization once per app lifetime. If initialization has already been
        // performed, we have nothing to do in this method.
        if (mInitialized) return
            mInitialized = true

        //startFetchTasksService()
        //mDownloadedTasks = networkDataSource.getCurrentData()
    }




    // As long as the repository exists, observe the network LiveData.
    // If that LiveData changes, update the database.

    /**
     * Database related operations

    override fun getCurrentData(): MutableLiveData<Array<String>>? {
        initializeData()
        return networkDataSource.getCurrentData()
    }*/

    /**
     * Deletes old weather data because we don't need to keep multiple days' data
     */
    override fun deleteOldData() {
        tasksRepo.deleteAllTasksDB()
    }

    /**
     * Checks if there are enough days of future weather for the app to display all the needed data.
     *
     * @return Whether a fetch is needed
     */
    override fun isFetchNeeded(): Boolean {
        //val today = SunshineDateUtils.getNormalizedUtcDateForToday()
        //val count = mWeatherDao.countAllFutureWeather(today)
        return true
    }

    override fun getCurrentData(): MutableLiveData<Array<String>>? {
        TODO("Not yet implemented")
    }

    /**
     * Network related operation

    override fun startFetchTasksService() {
        networkDataSource.startFetchDataService(androidContext)
    }*/




}
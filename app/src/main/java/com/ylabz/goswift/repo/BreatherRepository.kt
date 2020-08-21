package com.ylabz.goswift.repo

import androidx.lifecycle.LiveData
import com.ylabz.goswift.db.Breather
import com.ylabz.goswift.db.BreatherDao

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class BreatherRepository(private val breatherDao: BreatherDao) {
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allBreathers: LiveData<List<Breather>> = breatherDao.allLiveBreathers()

    suspend fun insert(breather: Breather) {
        breatherDao.insert(breather)
    }

    suspend fun deleteAll() {
        breatherDao.deleteAll()
    }

    suspend fun deleteTopOne(breather: Breather) {
        breatherDao.delete(breather)
    }

    suspend fun getListBreather(): List<Breather> {
        return breatherDao.getList()
    }

}

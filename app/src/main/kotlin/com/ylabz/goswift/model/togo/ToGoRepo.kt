package com.ylabz.goswift.model.togo.ToGoRepo

import com.ylabz.goswift.model.togo.ToGoDB.ToGo
import com.ylabz.goswift.model.togo.ToGoDB.ToGoDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//constructor(private val wordDao: WordDao, private val retrofit: Retrofit) {
class ToGoRepo @Inject constructor(private val toGoDao: ToGoDao) {

    /*suspend fun insert(word: Word) {
        dataDao.insert(word)
    }

    suspend fun delete() {
        dataDao.deleteAll()
    }*/

    fun getToGoInfo() : Flow<List<ToGo>> {
        return toGoDao.getAll()
    }

    private val TAG: String = "GoSwift"
}
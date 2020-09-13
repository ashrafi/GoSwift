package com.ylabz.goswift.model.togo.ToGoRepo

import com.ylabz.goswift.model.togo.GoToEvntDB.GoToEnvt
import com.ylabz.goswift.model.togo.GoToEvntDB.GoToEvntDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject



//constructor(private val wordDao: WordDao, private val retrofit: Retrofit) {
class GoToEvntRepo @Inject constructor(private val goToEvntDao: GoToEvntDao) {

    /*suspend fun insert(word: Word) {
        dataDao.insert(word)
    }

    suspend fun delete() {
        dataDao.deleteAll()
    }*/

    fun getToGoInfo(): Flow<List<GoToEnvt>> {
        return goToEvntDao.getAll()
    }

    suspend fun insert(goToEnvtItem: GoToEnvt) {
        goToEvntDao.insert(goToEnvtItem)
    }

    suspend fun deleteAll() {
        goToEvntDao.deleteAll()
    }

    private val TAG: String = "GoSwift"
}
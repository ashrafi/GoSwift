package com.ylaz.goswift.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ylabz.goswift.db.Breather
import com.ylabz.goswift.repo.BreatherRepository
import com.ylabz.goswift.db.BreatherRoomDatabase
import kotlinx.coroutines.launch

class BreatherViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: BreatherRepository
    // LiveData gives us updated words when they change.
    val allBreathers: LiveData<List<Breather>>

    init {
        // Gets reference to WordDao from WordRoomDatabase to construct
        // the correct WordRepository.
        val breathersDao =
            BreatherRoomDatabase.getDatabase(application, viewModelScope).breatherDao()
        repository = BreatherRepository(breathersDao)
        allBreathers = repository.allBreathers
    }

    /**
     * The implementation of insert() in the database is completely hidden from the UI.
     * Room ensures that you're not doing any long running operations on the mainthread, blocking
     * the UI, so we don't need to handle changing Dispatchers.
     * ViewModels have a coroutine scope based on their lifecycle called viewModelScope which we
     * can use here.
     */
    fun insert(breather: Breather) = viewModelScope.launch {
        repository.insert(breather)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun deleteOne(breather: Breather) = viewModelScope.launch {
        repository.deleteTopOne(breather)
    }

    fun getBreathers(): List<Breather>? {
        var list: List<Breather>? = null
        viewModelScope.launch {
            list = repository.getListBreather()
        }
        return list
    }
}
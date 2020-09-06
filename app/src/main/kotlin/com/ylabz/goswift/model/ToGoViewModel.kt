package com.ylabz.goswift.model

import android.app.Application
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ylabz.goswift.model.togo.ToGoDB.ToGo
import com.ylabz.goswift.model.togo.ToGoRepo.ToGoRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

// do not use live data
// import androidx.lifecycle.LiveData

/**
 * the viewmodel is started in the onCreateView() in a Fragment
 */
class ToGoViewModel @ViewModelInject constructor(
    private val toGoRepo: ToGoRepo,
    @Assisted private val savedStateHandle: SavedStateHandle,
    applicationContext: Application
) : AndroidViewModel(applicationContext) {
    // TODO: Implement the ViewModel
    lateinit var toGoTasks: Flow<List<ToGo>>

    init {
        viewModelScope.launch {
            toGoTasks = toGoRepo.getToGoInfo()
        }
    }

    //make repo call
    private val TAG: String? = "GoSwift"
    // The ViewModel maintains a reference to the repository to get data.

    fun getToGoInfo(): Flow<List<ToGo>> {
        return toGoTasks
    }

    fun addToGoItem(toGoItem: ToGo) {
        viewModelScope.launch(Dispatchers.IO) {
            toGoRepo.insert(toGoItem)
        }
    }

}

/* Example done right
https://github.com/romainguy/sample-materials-shop/blob/main/app/src/main/kotlin/com/curiouscreature/compose/sample/shop/Store.kt
 */
package com.ylabz.goswift.model

import android.app.Application
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ylabz.goswift.model.togo.GoToEvtDB.GoToEvt
import com.ylabz.goswift.model.togo.GoToEvtRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

// do not use live data
// import androidx.lifecycle.LiveData

/**
 * the viewmodel is started in the onCreateView() in a Fragment
 */
class GoToEvtViewModel @ViewModelInject constructor(
    private val goToEvtRepo: GoToEvtRepo,
    @Assisted private val savedStateHandle: SavedStateHandle,
    applicationContext: Application
) : AndroidViewModel(applicationContext) {
    // TODO: Implement the ViewModel
    lateinit var goToEvtTasks: Flow<List<GoToEvt>>

    init {
        viewModelScope.launch {
            goToEvtTasks = goToEvtRepo.getToGoInfo()
        }
    }

    //make repo call
    private val TAG: String? = "GoSwift"
    // The ViewModel maintains a reference to the repository to get data.

    fun getToGoInfo(): Flow<List<GoToEvt>> {
        return goToEvtTasks
    }

    fun addToGoItem(goToEnvtItem: GoToEvt) {
        viewModelScope.launch(Dispatchers.IO) {
            goToEvtRepo.insert(goToEnvtItem)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            goToEvtRepo.deleteAll()
        }

    }

}

/* Example done right
https://github.com/romainguy/sample-materials-shop/blob/main/app/src/main/kotlin/com/curiouscreature/compose/sample/shop/Store.kt
 */
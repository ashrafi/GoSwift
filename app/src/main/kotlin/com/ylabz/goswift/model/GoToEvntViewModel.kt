package com.ylabz.goswift.model

import android.app.Application
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ylabz.goswift.model.togo.GoToEvntDB.GoToEnvt
import com.ylabz.goswift.model.togo.ToGoRepo.GoToEvntRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

// do not use live data
// import androidx.lifecycle.LiveData

/**
 * the viewmodel is started in the onCreateView() in a Fragment
 */
class GoToEvntViewModel @ViewModelInject constructor(
    private val goToEvntRepo: GoToEvntRepo,
    @Assisted private val savedStateHandle: SavedStateHandle,
    applicationContext: Application
) : AndroidViewModel(applicationContext) {
    // TODO: Implement the ViewModel
    lateinit var goToEnvtTasks: Flow<List<GoToEnvt>>

    init {
        viewModelScope.launch {
            goToEnvtTasks = goToEvntRepo.getToGoInfo()
        }
    }

    //make repo call
    private val TAG: String? = "GoSwift"
    // The ViewModel maintains a reference to the repository to get data.

    fun getToGoInfo(): Flow<List<GoToEnvt>> {
        return goToEnvtTasks
    }

    fun addToGoItem(goToEnvtItem: GoToEnvt) {
        viewModelScope.launch(Dispatchers.IO) {
            goToEvntRepo.insert(goToEnvtItem)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            goToEvntRepo.deleteAll()
        }

    }

}

/* Example done right
https://github.com/romainguy/sample-materials-shop/blob/main/app/src/main/kotlin/com/curiouscreature/compose/sample/shop/Store.kt
 */
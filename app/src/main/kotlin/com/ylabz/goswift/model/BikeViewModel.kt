package com.ylabz.goswift.model

import android.app.Application
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ylabz.goswift.model.bike.StationRepo
import com.ylabz.goswift.model.bike.stationDB.Station
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

// do not use live data
// import androidx.lifecycle.LiveData

/**
 * the viewmodel is started in the onCreateView() in a Fragment
 */
class BikeViewModel @ViewModelInject constructor(
    private val stationRepo: StationRepo,
    @Assisted private val savedStateHandle: SavedStateHandle,
    applicationContext: Application
)  : AndroidViewModel(applicationContext) {
    // TODO: Implement the ViewModel

    init {
        // Setup State
        viewModelScope.launch {
            stationRepo.callGBFSAPI()
        }

    }


    //make repo call
    private val TAG: String? = "GoSwift"
    // The ViewModel maintains a reference to the repository to get data.

    fun getBikeInfo(): Flow<List<Station>> {
        return stationRepo.getSomeBikeInfo()
    }

}
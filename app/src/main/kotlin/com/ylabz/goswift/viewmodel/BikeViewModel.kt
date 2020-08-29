package com.ylabz.goswift.viewmodels

import android.app.Application
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ylabz.goswift.data.database.db.StationRoomDB
import com.ylabz.goswift.data.database.model.Station
import com.ylabz.goswift.repo.BikeRepository


/**
 * the viewmodel is started in the onCreateView() in a Fragment
 */
class BikeViewModel @ViewModelInject constructor(applicationContext: Application)  : AndroidViewModel(applicationContext) {
    // TODO: Implement the ViewModel

    //make repo call
    private val TAG: String? = "Breather"
    // The ViewModel maintains a reference to the repository to get data.
    private val bikeRepo : BikeRepository

    init {
        Log.v(TAG, "Bike ViewModel Called")
        // Gets reference to WordDao from WordRoomDatabase to construct
        // the correct WordRepository.
        val stationDao = StationRoomDB.getStationRoomDB(applicationContext,viewModelScope).stationDao()

        bikeRepo = BikeRepository(stationDao)


    }

    fun getBikeInfo(): LiveData<List<Station>> {

        return bikeRepo.getSomeBikeInfo()

    }

}
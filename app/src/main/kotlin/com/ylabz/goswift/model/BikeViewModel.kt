package com.ylabz.goswift.model

import android.annotation.SuppressLint
import android.app.Application
import android.location.Location
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.ylabz.goswift.model.bike.StationRepo
import com.ylabz.goswift.model.bike.stationDB.StationInfoDB
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
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    init {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(applicationContext)

        // Setup State
        viewModelScope.launch {
            stationRepo.callGBFSAPI()
        }
    }




    //make repo call
    private val TAG: String? = "GoSwift"
    // The ViewModel maintains a reference to the repository to get data.

    fun getBikeInfo(): Flow<List<StationInfoDB>> {
        return stationRepo.getSomeBikeInfo()
    }

    @SuppressLint("MissingPermission")
    fun getClosestStation(): Location {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                // Got last known location. In some rare situations this can be null.
            }

        val targetLocation = Location("") //provider name is unnecessary

        targetLocation.latitude = 0.0 //your coords of course
        targetLocation.longitude = 0.0

        //val distanceInMeters = targetLocation.distanceTo(myLocation)

        return targetLocation
    }

}
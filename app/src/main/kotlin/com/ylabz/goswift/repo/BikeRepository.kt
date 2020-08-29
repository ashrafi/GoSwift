package com.ylabz.goswift.repo

import android.util.Log
import androidx.lifecycle.LiveData
import com.ylabz.goswift.api.bike.BikeAPI
import com.ylabz.goswift.api.bike.data.BikeStationInfo
import com.ylabz.goswift.data.database.StationDao
import com.ylabz.goswift.data.database.model.Station
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import retrofit2.awaitResponse

//make repo call
private val TAG: String? = "GoSwift"

/**
 * Connects to both the [remote data source object] -- retrofit
 * and
 * ROOM
 *
 * Very good example
 * https://github.com/android/architecture-components-samples/blob/master/GithubBrowserSample/app/src/main/java/com/android/example/github/repository/NetworkBoundResource.kt
 *
 * How the Repo works
 * https://medium.com/@eladb4382/paging-library-viewmodel-livedata-room-and-retrofit-66bf6a0eef9d
 *
 */

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class BikeRepository(private val stationDao: StationDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.

    init{
        Log.v(TAG, "Bike Repo")
        // When the Repo starts -- populate the DB with network info
        // populate -and-or-  the ROOM DB
        populateRoom()

    }


    // this should be moved to the network access object.
    private fun populateRoom(){//} : BikeStationInfo?{


        BikeAPI.bikeStationService.getBikeStationInfo()


        Log.v(TAG, "bike System Service Sent")
        GlobalScope.async {
            Log.v(TAG, "bike System --> " +  BikeAPI.bikeAPIService.getBikeAPInfo().awaitResponse().body().toString())
        }

        Log.v(TAG, "bike Stations Service Sent")
        GlobalScope.async {
            // Write to the ROOM DB
            val stationList : BikeStationInfo? = BikeAPI.bikeStationService.getBikeStationInfo().awaitResponse().body()
            Log.v(TAG, "bike stations -->$stationList")
            stationList?.data?.stations?.forEach{
                stationDao.insert(Station(it.name, it.lat, it.lon, it.capacity))
            }
        }

        Log.v(TAG, "bike API Service Sent")
        GlobalScope.async {
            Log.v(TAG, "bike API -->" + BikeAPI.bikeSysService.getBikeSysInfo().awaitResponse().body().toString())
        }


        // return bikeInfo

    }


    fun getSomeBikeInfo() : LiveData<List<Station>> {
        return stationDao.getAll()
    }


}
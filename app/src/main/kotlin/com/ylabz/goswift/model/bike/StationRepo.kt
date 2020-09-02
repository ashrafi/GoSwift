package com.ylabz.goswift.model.bike

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.ylabz.goswift.api.bike.data.BikeStationInfo
import com.ylabz.goswift.di.NetworkModule
import com.ylabz.goswift.model.bike.stationDB.Station
import com.ylabz.goswift.model.bike.stationDB.StationDao
import kotlinx.coroutines.flow.Flow
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

//constructor(private val wordDao: WordDao, private val retrofit: Retrofit) {
class StationRepo @Inject constructor(private val stationDao: StationDao/*, private val retrofit: Retrofit*/) {

    /*suspend fun insert(word: Word) {
        dataDao.insert(word)
    }

    suspend fun delete() {
        dataDao.deleteAll()
    }*/

    fun getSomeBikeInfo() : Flow<List<Station>> {
        return stationDao.getAll()
    }

    /**
     * Need to have this for now.  DI is not working
     */
    private val BIKE_STATION_BASE_URL = "https://gbfs.fordgobike.com/"
    private val bike_retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()))
            .baseUrl(BIKE_STATION_BASE_URL)
            .client(OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build())
            .build()


    // Retrofit Suspend Call
    suspend fun callGBFSAPI() {
        val stationAPI = bike_retrofit.create(NetworkModule.BikeStationApiService::class.java)
        //val stationAPI = retrofit.create(NetworkModule.BikeStationApiService::class.java)
        val stationList : BikeStationInfo? = stationAPI.getBikeStationInfo().awaitResponse().body()

        Log.v(TAG, "bike stations -->$stationList")
        stationList?.data?.stations?.forEach{
            stationDao.insert(Station(it.name, it.lat, it.lon, it.capacity))
        }

        /*val exCall = stationAPI.getExampleInfo().awaitResponse().body()
        wordDao.insert(Word("Title -> ${exCall?.title}"))
        wordDao.insert(Word("User ID -> ${exCall?.userId}"))
        wordDao.insert(Word("id -> ${exCall?.id}"))*/
    }

    private val TAG: String = "GoSwift"
}
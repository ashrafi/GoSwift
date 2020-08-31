package com.ylabz.goswift.api.bike

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.ylabz.goswift.api.bike.data.BikeAPIs
import com.ylabz.goswift.api.bike.data.BikeStationInfo
import com.ylabz.goswift.api.bike.data.BikeSysInfo
import com.ylabz.goswift.di.NetworkModule
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

//https://gbfs.fordgobike.com/gbfs/gbfs.json
//https://guides.codepath.com/android/consuming-apis-with-retrofit

// Add this to the mars land data.
// https://github.com/Altynay1405/andfun-kotlin-mars-real-estate-Starter-Code/blob/master/app/src/main/java/com/example/android/marsrealestate/network/MarsApiService.kt

// Kotlin Retrofit objects
// https://github.com/mfdz/flinkster2gbfs/blob/master/src/main/kotlin/de/mfdz/flinkster2gbfs/gbfs.kt

//Generate API
//http://www.jsonschema2pojo.org/




private val bike_retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(client)
    .build()

object BikeAPIDoNotUser {
    val bikeSysService: NetworkModule.BikeSysApiService by lazy {
        bike_retrofit.create(NetworkModule.BikeSysApiService::class.java)
    }

    val bikeStationService: NetworkModule.BikeStationApiService by lazy {
        bike_retrofit.create(NetworkModule.BikeStationApiService::class.java)
    }

    val bikeAPIService: NetworkModule.BikeInfoAPIService by lazy {
        bike_retrofit.create(NetworkModule.BikeInfoAPIService::class.java)
    }
}
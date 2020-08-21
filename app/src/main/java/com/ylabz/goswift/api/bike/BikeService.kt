package com.zoewave.breathezy.api.bike

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.zoewave.breathezy.api.bike.data.BikeAPIs
import com.zoewave.breathezy.api.bike.data.BikeStationInfo
import com.zoewave.breathezy.api.bike.data.BikeSysInfo
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

//make repo call
private val TAG: String? = "Breather"
private val BASE_URL = "https://gbfs.fordgobike.com/"

interface BikeSysApiService {
    @GET("gbfs/en/system_information.json") //endpoint
    fun getBikeSysInfo(): Call<BikeSysInfo>
}

interface BikeStationApiService {
    @GET("gbfs/en/station_information.json") //endpoint
    fun getBikeStationInfo(): Call<BikeStationInfo>
    //Json4Kotlin_Base
    //BikeStationInfo
}

interface BikeInfoAPIService {
    @GET("gbfs/gbfs.json") //endpoint
    fun getBikeAPInfo(): Call<BikeAPIs>
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val logging =  HttpLoggingInterceptor()

val client =  OkHttpClient.Builder().addInterceptor(logging.setLevel(HttpLoggingInterceptor.Level.BODY)).build();

private val bike_retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(client)
    .build()

object BikeAPI {
    val bikeSysService: BikeSysApiService by lazy {
        bike_retrofit.create(BikeSysApiService::class.java)
    }

    val bikeStationService: BikeStationApiService by lazy {
        bike_retrofit.create(BikeStationApiService::class.java)
    }

    val bikeAPIService: BikeInfoAPIService by lazy {
        bike_retrofit.create(BikeInfoAPIService::class.java)
    }
}
package com.ylabz.goswift.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.ylabz.goswift.api.bike.data.BikeAPIs
import com.ylabz.goswift.api.bike.data.BikeStationInfo
import com.ylabz.goswift.api.bike.data.BikeSysInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import javax.inject.Singleton

object NetworkModule {

    @Module
    @InstallIn(ApplicationComponent::class)
    object NetworkModule {

        @Provides
        @Singleton
        fun provideAuthInterceptorOkHttpClient(
            logging: HttpLoggingInterceptor
        ): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(logging.setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        }

        @Provides
        fun provideLogging(): HttpLoggingInterceptor {
            return HttpLoggingInterceptor()
        }

        @Provides
        fun provideMoshi(): Moshi {
            return Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        }

        @Provides
        @Singleton
        fun provideRetrofit(
            okHttpClient: OkHttpClient,
            moshi: Moshi
        ): Retrofit {
            return Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(okHttpClient)
                .baseUrl(BIKE_STATION_BASE_URL)
                .build()
        }
    }

    //make repo call
    private val TAG: String? = "GoSwift"
    private val BIKE_STATION_BASE_URL = "https://gbfs.fordgobike.com/"

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
}
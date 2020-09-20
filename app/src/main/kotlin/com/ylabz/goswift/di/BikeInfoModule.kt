package com.ylabz.goswift.di

import com.ylabz.goswift.model.bike.stationDB.StationInfoDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

// application scope
@InstallIn(ApplicationComponent::class)
@Module
object BikeInfoModule {
    @Provides
    @Singleton
    fun provideStationID(): StationID {
        return StationID()
    }

    @Provides
    @Singleton
    fun provideStationInfoDB(): StationInfoDB {
        return StationInfoDB(capacity = 25, lat = 0.0, lon = 0.0)
    }


}

data class StationID(var name: String ="none", val id: Int = 0)
package com.ylabz.goswift.di

import com.ylabz.goswift.model.bike.StationRepo
import com.ylabz.goswift.model.bike.stationDB.StationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

object RepoModule {
    @InstallIn(ActivityRetainedComponent::class)
    @Module
    object RepoModule {

        @Provides
        @ActivityRetainedScoped
        fun provideStatoionRepo(stationDao: StationDao/*, retrofit: Retrofit*/): StationRepo {
            return StationRepo(stationDao/*, retrofit*/)
        }
    }

}
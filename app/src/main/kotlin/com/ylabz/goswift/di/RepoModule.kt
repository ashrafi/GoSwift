package com.ylabz.goswift.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

object RepoModule {
    @InstallIn(ActivityRetainedComponent::class)
    @Module
    object RepoModule {

        /*@Provides
        @ActivityRetainedScoped
        fun provideStatoionRepo(stationDao: StationDao/*, retrofit: Retrofit*/): StationRepo {
            return StationRepo(stationDao/*, retrofit*/)
        }*/


        /*@Provides
        @ActivityRetainedScoped
        fun provideToGoRepo(toGoDao: GoToEvntDao/*, retrofit: Retrofit*/): GoToEvntRepo {
            return GoToEvntRepo(toGoDao/*, retrofit*/)
        }*/
    }

}
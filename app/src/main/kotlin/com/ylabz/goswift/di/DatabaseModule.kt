package com.ylabz.goswift.di

import android.content.Context
import androidx.room.Room
import com.ylabz.goswift.model.bike.stationDB.StationDao
import com.ylabz.goswift.model.bike.stationDB.StationRoomDB
import com.ylabz.goswift.model.togo.GoToEvntDB.GoToEvntDao
import com.ylabz.goswift.model.togo.GoToEvntDB.ToGoRoomDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

// application scope
@InstallIn(ApplicationComponent::class)
@Module

object DatabaseModule {
    @Provides
    @Singleton
    fun provideStationDatabase(@ApplicationContext appContext: Context): StationRoomDB {
        return Room.databaseBuilder(
            appContext,
            StationRoomDB::class.java,
            "bike.station.db"
        ).build()
    }

    @Provides
    fun stationDao(database: StationRoomDB): StationDao {
        return database.stationDao()
    }

    @Provides
    @Singleton
    fun provideGoToDatabase(@ApplicationContext appContext: Context): ToGoRoomDB {
        return Room.databaseBuilder(
            appContext,
            ToGoRoomDB::class.java,
            "to.go.db"
        ).build()
    }

    @Provides
    fun goToDao(database: ToGoRoomDB): GoToEvntDao {
        return database.goToEvntDao()
    }
}
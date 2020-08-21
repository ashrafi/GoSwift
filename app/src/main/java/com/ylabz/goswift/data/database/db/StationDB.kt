package com.ylabz.goswift.data.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ylabz.goswift.data.database.StationDao
import com.ylabz.goswift.data.database.model.Station
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Station::class), version = 1, exportSchema = false)
abstract class StationRoomDB : RoomDatabase() {


    abstract fun stationDao(): StationDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: StationRoomDB? = null

        fun getStationRoomDB(
            context: Context,
            scope: CoroutineScope
        ): StationRoomDB {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return StationRoomDB.INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StationRoomDB::class.java,
                    "bike_station_database"
                )
                    .addCallback(StationDatabaseCallback(scope))
                    .build()
                StationRoomDB.INSTANCE = instance
                // return instance
                instance
            }
        }
    }

    private class StationDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        /**
         * Override the onOpen method to populate the database.
         * For this sample, we clear the database every time it is created or opened.
         */
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            // If you want to keep the data through app restarts,
            // comment out the following line.
            StationRoomDB.INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.stationDao())
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateDatabase(stationDao: StationDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            //stationDao.deleteAll()

            stationDao.insert(Station("ash", 0.0, 0.0, 5))
        }
    }










}
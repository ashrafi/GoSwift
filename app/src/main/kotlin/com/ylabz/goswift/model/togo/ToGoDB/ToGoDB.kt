package com.ylabz.goswift.model.togo.ToGoDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = arrayOf(ToGo::class), version = 1, exportSchema = false)
abstract class ToGoRoomDB : RoomDatabase() {


    abstract fun toGoDao(): ToGoDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: ToGoRoomDB? = null

        fun getToGoRoomDB(
            context: Context,
            scope: CoroutineScope
        ): ToGoRoomDB {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ToGoRoomDB::class.java,
                    "to_go_database"
                )
                    .addCallback(ToGoDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

    private class ToGoDatabaseCallback(
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
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.toGoDao())
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateDatabase(toGoDao: ToGoDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            toGoDao.insert(ToGo("ash", 0.0, 0.0))
        }
    }










}
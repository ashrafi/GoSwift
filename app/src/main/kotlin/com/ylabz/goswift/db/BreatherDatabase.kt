package com.ylabz.goswift.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = arrayOf(Breather::class), version = 1, exportSchema = false)
abstract class BreatherRoomDatabase : RoomDatabase() {

    abstract fun breatherDao(): BreatherDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: BreatherRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): BreatherRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BreatherRoomDatabase::class.java,
                    "breather_database"
                )
                    .addCallback(BreatherDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

    private class BreatherDatabaseCallback(
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
                    populateDatabase(database.breatherDao())
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateDatabase(breathDao: BreatherDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            breathDao.deleteAll()

            var word = Breather(firstName = "Hello", lastName = "Breather")
            breathDao.insert(word)
            word = Breather(firstName = "World!", lastName = "Hello")
            breathDao.insert(word)
        }
    }


}

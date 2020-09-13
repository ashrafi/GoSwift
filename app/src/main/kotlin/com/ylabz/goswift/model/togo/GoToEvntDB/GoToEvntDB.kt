package com.ylabz.goswift.model.togo.GoToEvntDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.OffsetDateTime


@Database(entities = arrayOf(GoToEnvt::class), version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ToGoRoomDB : RoomDatabase() {


    abstract fun goToEvntDao(): GoToEvntDao

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
                    .addCallback(GoToDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

    private class GoToDatabaseCallback(
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
                    populateDatabase(database.goToEvntDao())
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateDatabase(goToEvntDao: GoToEvntDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            //goToEvntDao.insert(GoToEnvt("ash", 0.0, 0.0, OffsetDateTime.now(), "img"))
            goToEvntDao.insert(GoToEnvt("ash", 0.0, 0.0, "img"))
        }
    }










}
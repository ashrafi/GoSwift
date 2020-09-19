package com.ylabz.goswift.model.togo

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.CalendarContract
import android.util.Log
import com.ylabz.goswift.model.togo.GoToEvtDB.GoToEvt
import com.ylabz.goswift.model.togo.GoToEvtDB.GoToEvtDao
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


// The indices for the projection array above.
private const val PROJECTION_ID_INDEX: Int = 0
private const val PROJECTION_ACCOUNT_NAME_INDEX: Int = 1
private const val PROJECTION_DISPLAY_NAME_INDEX: Int = 2
private const val PROJECTION_OWNER_ACCOUNT_INDEX: Int = 3

// Projection array. Creating indices for this array instead of doing
// dynamic lookups improves performance.
private val EVENT_PROJECTION: Array<String> = arrayOf(
    CalendarContract.Calendars._ID,                     // 0
    CalendarContract.Calendars.ACCOUNT_NAME,            // 1
    CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,   // 2
    CalendarContract.Calendars.OWNER_ACCOUNT            // 3
)

//constructor(private val wordDao: WordDao, private val retrofit: Retrofit) {
class GoToEvtRepo @Inject constructor(private val goToEvtDao: GoToEvtDao, @ApplicationContext appContext: Context) {
    var contentResolver: ContentResolver = appContext.contentResolver
    /*suspend fun insert(word: Word) {
        dataDao.insert(word)
    }

    suspend fun delete() {
        dataDao.deleteAll()
    }*/

    fun getToGoInfo(): Flow<List<GoToEvt>> {
        //synCalInfo()
        return goToEvtDao.getAll()
    }

    suspend fun insert(goToEvtItem: GoToEvt) {
        goToEvtDao.insert(goToEvtItem)
    }

    suspend fun deleteAll() {
        goToEvtDao.deleteAll()
    }


    /**
     * get the info out of the calendar and put in ROOM DB
     *
     * https://www.codexpedia.com/android/crud-operations-using-calendar-provider-in-android/
     */
    fun synCalInfo() {
        // Run query
        val uri: Uri = CalendarContract.Calendars.CONTENT_URI
        val selection: String = "((${CalendarContract.Calendars.ACCOUNT_NAME} = ?) AND (" +
                "${CalendarContract.Calendars.ACCOUNT_TYPE} = ?) AND (" +
                "${CalendarContract.Calendars.OWNER_ACCOUNT} = ?))"
        val selectionArgs: Array<String> = arrayOf(
            "biologica@gmail.com",//"demo@ylabz.com", //ACCOUNT_NAME
            "com.gmail",//"com.ylabz", //ACCOUNT_TYPE
            "biologica@gmail.com"//"demo@ylabz.com" //OWNER_ACCOUNT
        )
        val cur: Cursor = contentResolver.query(
            uri,
            EVENT_PROJECTION,
            selection,
            selectionArgs,
            null
        )!!

        //add to ROOM DB
        // Use the cursor to step through the returned records
        while (cur.moveToNext()) {
            // Get the field values
            val calID: Long = cur.getLong(PROJECTION_ID_INDEX)
            val displayName: String = cur.getString(PROJECTION_DISPLAY_NAME_INDEX)
            val accountName: String = cur.getString(PROJECTION_ACCOUNT_NAME_INDEX)
            val ownerName: String = cur.getString(PROJECTION_OWNER_ACCOUNT_INDEX)
            Log.v("GoSwift","Cal $displayName")
        }

    }

    private val TAG: String = "GoSwift"
}

// Read Cal into GoToEvtRepo

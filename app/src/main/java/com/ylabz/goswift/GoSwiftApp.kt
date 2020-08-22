package com.ylabz.goswift

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp()
open class GoSwiftApp : Application() {
    init {
        Log.v("GoSwift","This is the start")
    }
}
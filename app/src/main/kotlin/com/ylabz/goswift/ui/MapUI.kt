package com.ylabz.goswift.ui

import android.location.Location
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.ylabz.goswift.ui.components.DetailsActivityArg
import com.ylabz.goswift.ui.components.DetailsScreen

@Composable
fun MapUI() {
    // get current GoTo

    // get list of bikes

    // get the distance
    // https://stackoverflow.com/questions/41336756/find-the-closest-latitude-and-longitude/41337005


    Column {
        Text("Here")
        DetailsScreen(DetailsActivityArg("ash", "code", "37.7749", "-122.4194"))
    }

}


fun findStation() {
    var closestLocation: Location = Location("")
    var smallestDistance = -1

    /*val locations : Location[]

    for (location in locations) {
        val distance: Int = Location.distanceBetween(
            closestLocation.latitude,
            closestLocation.longitude,
            location.latitude,
            location.longitude
        )
        if (smallestDistance == -1 || distance < smallestDistance) {
            closestLocation = location
            smallestDistance = distance
        }
    }*/


}
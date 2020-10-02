package com.ylabz.goswift.ui.bike

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ylabz.goswift.model.bike.stationDB.StationInfoDB
import com.ylabz.goswift.ui.utils.NavScreen
import com.ylabz.goswift.ui.utils.navigateTo


@Composable
fun BikeDetailsScreen(stationID : StationInfoDB) {

    Column(verticalArrangement = Arrangement.Center) {
        Spacer(Modifier.preferredHeight(32.dp))

        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stationID.capacity.toString(),
            style = MaterialTheme.typography.h6
        )
        Spacer(Modifier.preferredHeight(16.dp))
        Button(
            modifier = Modifier.padding(16.dp),
            elevation = 5.dp,
            onClick = {
                navigateTo(NavScreen.Bike)
            }) {
            // The Button composable allows you to provide child composables that inherit this button
            // functionality.
            // The Text composable is pre-defined by the Compose UI library; you can use this
            // composable to render text on the screen
            Text(text = "Home button", modifier = Modifier.padding(16.dp))
        }
        Spacer(Modifier.preferredHeight(16.dp))
        //JustMapUI(stationID.lat.toString(), stationID.lon.toString())
        //CityMapView(args.latitude, args.longitude)
    }
}
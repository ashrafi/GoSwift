package com.ylabz.goswift.ui

import android.util.Log
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.lazy.LazyColumnItems
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.launchInComposition
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import androidx.lifecycle.LiveData
import com.ylabz.goswift.data.database.model.Station
import com.ylabz.goswift.viewmodels.BikeViewModel
import javax.inject.Inject

/**
 * add a list of bike stations.
 */
class BikeComposeUI @Inject constructor() {

    @Composable
    fun Greeting(name: String) {
        Column() {
            Text(text = "Hello From Bike $name!")
            bikeStationList()
            SimpleButtonComponentBike()
        }
    }

    @Composable
    fun bikeStationList() {
        val bikeViewModel = viewModel<BikeViewModel>()
        //launchInComposition {}
        val stationList by bikeViewModel.getBikeInfo().observeAsState(initial = emptyList())
        Log.v("GoSwift", "data is ${stationList.size}")
        Column() {
            LazyColumnFor(items = stationList, itemContent = { bikeS ->
                Card(
                    //color = Color.Cyan,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.padding(8.dp)
                )
                {
                    Text("Bike Station at ${bikeS.stationName}")
                }
            })
        }
    }

    @Composable
    fun SimpleButtonComponentBike() {

        // Button is a pre-defined Material Design implementation of a contained button -
        // https://material.io/design/components/buttons.html#contained-button.

        // You can think of Modifiers as implementations of the decorators pattern that are used to
        // modify the composable that its applied to. In this example, we assign a padding of
        // 16dp to the Button.
        Button(
            modifier = Modifier.padding(16.dp),
            elevation = 5.dp,
            onClick = {
                navigateTo(NavScreen.Home)
            }) {
            // The Button composable allows you to provide child composables that inherit this button
            // functionality.
            // The Text composable is pre-defined by the Compose UI library; you can use this
            // composable to render text on the screen
            Text(text = "Nav Home", modifier = Modifier.padding(16.dp))
        }
    }
}
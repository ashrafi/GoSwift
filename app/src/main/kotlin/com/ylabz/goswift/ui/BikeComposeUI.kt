package com.ylabz.goswift.ui

import android.util.Log
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import androidx.ui.tooling.preview.Preview
import com.ylabz.goswift.model.BikeViewModel
import javax.inject.Inject

/**
 * add a list of bike stations.
 */
class BikeComposeUI @Inject constructor() {

    @Composable
    fun Greeting(name: String) {
        Column {
            Text(text = "Hello From Bike $name!")
            bikeStationList()
            //SimpleButtonComponentBike()
        }
    }

    @Composable
    fun bikeStationList() {
        val bikeViewModel = viewModel<BikeViewModel>()
        //launchInComposition {}
        // Live Data
        //val stationList by bikeViewModel.getBikeInfo().observeAsState(initial = emptyList())
        // Flow
        val stationList = bikeViewModel.getBikeInfo().collectAsState(initial = emptyList()).value
        Log.v("GoSwift", "data is ${stationList.size}")
        Column {
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



    @Preview
    @Composable
    fun PreviewBikeComposeUI() {
        Greeting("hi")
    }
}
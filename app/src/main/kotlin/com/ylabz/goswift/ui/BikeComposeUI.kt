package com.ylabz.goswift.ui

import androidx.compose.foundation.Box
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import androidx.ui.tooling.preview.Preview
import com.google.gson.Gson
import com.ylabz.goswift.model.BikeViewModel
import com.ylabz.goswift.model.bike.stationDB.StationInfoDB
import com.ylabz.goswift.ui.components.JustMapUI

/**
 * add a list of bike stations.
 */


@Composable
fun bikeInfo(name: String) {
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

    /* Static Data
    val stationList = arrayListOf<StationInfoDB>()
    stationList.add(singleStation())
    Log.v("GoSwift", "data is ${stationList.size}")
    */
    Column {
        LazyColumnFor(items = stationList, itemContent = { bikeS ->
            Card(
                contentColor = Color.DarkGray, //colors[it % colors.size],
                backgroundColor = Color.Cyan,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.padding(8.dp)
            ) {
                Box(
                    padding = 16.dp,
                    modifier = Modifier.height(200.dp),
                    gravity = Alignment.Center
                ) {
                    Row {
                        JustMapUI(bikeS.lat, bikeS.lon)
                        Text(
                            "Bike StationInfoDB at ${bikeS.stationName}",
                            style = MaterialTheme.typography.h5
                        )
                    }
                }

            }
            Card(
                shape = RoundedCornerShape(8.dp),
                backgroundColor = MaterialTheme.colors.surface,
            ) {
                Box(
                    padding = 16.dp,
                    modifier = Modifier.height(200.dp),
                    gravity = Alignment.Center
                ) {
                    Text(
                        text = "This is a card view",
                        style = MaterialTheme.typography.h4
                    )
                }
            }
        })
    }
}


@Preview
@Composable
fun PreviewBikeComposeUI() {
    bikeInfo("hi")
}

fun singleStation(): StationInfoDB {
    var jsonStringStation = """
        {
          "station_id": "55",
          "stationName" : "StationInfoDB Name"
          "eightd_station_services": [

          ],
          "lon": -122.4295585,
          "capacity": 27,
          "eightd_has_key_dispenser": false,
          "lat": 37.7770527,
          "external_id": "fde4cecd-7497-4bdb-a452-0e1a755e7759",
          "rental_methods": [
            "CREDITCARD",
            "KEY"
          ],
          "stationName": "Webster St at Grove St",
          "rental_uris": {
            "android": "https://sfo.lft.to/lastmile_qr_scan",
            "ios": "https://sfo.lft.to/lastmile_qr_scan"
          },
          "has_kiosk": true,
          "short_name": "SF-J20",
          "electric_bike_surcharge_waiver": false,
          "station_type": "classic",
          "region_id": "3",


          "Info": "https://www.lyft.com/bikes/bay-wheels/system-data",
          "General Bikeshare Feed Specification GBFS": "https://github.com/NABSA/gbfs/blob/master/gbfs.md"
        }
    """.trimIndent()

    val jsonStringStationInfoDB = """
    {
        "stationName":"Ash,
        "lat": 37.7770527,
        "lon": -122.4295585,
        "capacity": 27,
        "img" = "lyft_bike"
    }
    """.trimIndent()

    return Gson().fromJson(jsonStringStationInfoDB, StationInfoDB::class.java)
}

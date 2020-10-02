package com.ylabz.goswift.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.VerticalGradient
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.loadImageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.viewModel
import androidx.ui.tooling.preview.Preview
import com.ylabz.goswift.R
import com.ylabz.goswift.model.BikeViewModel
import com.ylabz.goswift.model.bike.stationDB.StationInfoDB
import com.ylabz.goswift.ui.components.JustMapUI
import com.ylabz.goswift.ui.utils.*
import dev.chrisbanes.accompanist.coil.CoilImage

/*
// https://github.com/lelandrichardson/compose-dogfooding/blob/main/app/src/main/java/com/example/dogfood/MovieCarousel.kt
// JMV / JS / Koltin Mutli-platform does not have these issues.
// var latitude ="37.773972"
// var longitude = "-122.431297"
*/

private var currentStation: StationInfoDB? = null

@Composable
fun HomeUI() {
    val bikeViewModel = viewModel<BikeViewModel>()
    val stationList = bikeViewModel.getBikeInfo().collectAsState(initial = emptyList()).value
    var currentStationIndex = remember { mutableStateOf(0) }

    if (stationList.isNotEmpty()) {
        currentStation = stationList.get(currentStationIndex.value)
    }

    Box() { // Stack
        // Map Background
        JustMapUI(currentStation)
        BoundingBox(currentStationIndex) {
            var id = currentStationIndex.value + 1
            if (id > stationList.size)
                currentStationIndex.value = 0
            else
                currentStationIndex.value = id
            currentStation = stationList.get(id)
        }
    }
}

@Composable
fun BoundingBox(id: MutableState<Int>, onPositionChanged: () -> Unit) {

    //.fillMaxHeight() // takes a percentage //.fillMaxWidth(),
    Box(
        modifier = Modifier.fillMaxSize()
            .border(BorderStroke(5.dp, Color.Red))
            .padding(bottom = 52.dp),
        alignment = Alignment.BottomCenter
    ) {
        // We used another Box composable to create a container that will have the shadow applied.
        Box(
            modifier = Modifier.fillMaxWidth()
                .preferredHeight(250.dp)
                .padding(16.dp)
                .drawShadow(
                    elevation = 30.dp,
                    shape = RoundedCornerShape(8.dp)
                )
                .border(BorderStroke(5.dp, Color.Magenta)),
            //shape = RoundedCornerShape(8.dp),
            alignment = Alignment.BottomEnd
        ) {
            BikeInfoBox(onPositionChanged)
        }
    }
}


@Composable
fun BikeInfoBox(
    onPositionChanged: () -> Unit,
    modifier: Modifier = Modifier
) {
    val resources = ContextAmbient.current.resources
    val col1 = listOf(Rose4, Lavender3, Rose2, Lavender3, Rose4)
    val col2 = listOf(Ocean3, Shadow3)
    Card(
        modifier = modifier.fillMaxSize(),//.padding(8.dp)
        backgroundColor = Color.Cyan
    ) {
        ConstraintLayout {

            // Constraint Refs
            val (stationID, stationInfo, bikeImage,
                chip) = createRefs()

            Column(modifier = Modifier.constrainAs(stationID) {
                // Constraint the left edge of title to the right edge of the image
                // and add a margin of 16dp
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                // Constraint the top edge of title to the top edge of the image
                //top.linkTo(image.top)
            }
            ) {
                //Text("Station Name:")
                Text(
                    currentStation?.stationName ?: "unkown", style = TextStyle(
                        fontFamily = FontFamily.Serif, fontWeight =
                        FontWeight.W900, fontSize = 14.sp
                    )
                )
            }

            Row(modifier = Modifier.constrainAs(stationInfo) {
                top.linkTo(stationID.bottom, margin = 5.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            ) {
                Text("Capacity:  ")
                Text(
                    currentStation?.capacity.toString() ?: "Unknown", style = TextStyle(
                        fontFamily = FontFamily.Serif, fontWeight =
                        FontWeight.W900, fontSize = 14.sp
                    )
                )
            }

            BikeImage(
                modifier = Modifier
                    .constrainAs(bikeImage) {
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom, margin = 1.dp)
                        end.linkTo(parent.end)
                    }
            )

            // Place Switch here
            // Chips
            Row(modifier = Modifier.padding(4.dp)
                .constrainAs(chip) {
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom, margin = 20.dp)
                    end.linkTo(parent.end)
                }
            ) {
                // Chip (stringResource(R.string.app_name) // localization
                //Chip(label = "  Next  ")
                nextButton("Prev", onPositionChanged)
                nextButton("Next", onPositionChanged)

                //Chip(label = "  Prev  ")
            }
        }
    }
}

/*
 modifier = Modifier.padding(16.dp),
        // Provide a custom shape for this button. In this example. we specify the button to have
        // round corners of 16dp radius.
        shape = RoundedCornerShape(16.dp),
        elevation = 5.dp
 */

@Composable
fun nextButton(label : String , onPositionChanged: () -> Unit) {
    Button( modifier = Modifier.padding(1.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp),
        onClick = onPositionChanged)
    {
        Text(text = label, //style = MaterialTheme.typography.h5)
            //modifier = Modifier//.border(1.dp, Color.Gray, RoundedCornerShape(50)).padding(horizontal = 10.dp, vertical = 2.dp),
            fontSize = 12.sp,
            color = Color.Gray)
    }
}

@Composable
fun Chip(
    modifier: Modifier = Modifier.padding(horizontal = 5.dp),
    label: String,
) {
    Text(
        label,
        modifier = modifier.border(1.dp, Color.Gray, RoundedCornerShape(50))
            .padding(horizontal = 8.dp, vertical = 2.dp),
        fontSize = 12.sp,
        color = Color.Gray,
    )
}

@Composable
fun BikeImage(modifier: Modifier) {
    Box(
        modifier = modifier
            .width(220.dp)
            .height(140.dp)
            //.preferredSize(120.dp)
            .clip(RoundedCornerShape(18.dp))
            .border(BorderStroke(5.dp, Color.Green))
    ) {// Stack
        //Spacer(Modifier.preferredHeight(16.dp))
        Image(
            //modifier = Modifier.fillMaxSize(),
            //fill the width of the column, cropped appropriate height.
            contentScale = ContentScale.Crop,
            asset = imageResource(R.drawable.fordbike),
        )
        Text(
            "bike image",
            color = Color.Yellow,
            style = typography.h6
        )
    }
}

@Preview
@Composable
fun PreviewHomeUI() {
    HomeUI()
    //Text("hi")
}

/**
 * below not used -- below not used -- below not used
 */

@Composable
fun RentBike(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        backgroundColor = Color.DarkGray,
        elevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text("Rent Bike", color = Color.White)
    }
}

/**
 * URL image
 * https://electrek.co/wp-content/uploads/sites/3/2018/11/tesla-electric-bicycle-3.jpg
 */
@Composable
fun CurrentRentInfoText() {
    val resources = ContextAmbient.current.resources
    // will not work with SVG
    val image = imageResource(R.drawable.fordbike)

    Column {
        /*CoilImage(
            data = imageUrl,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )*/
        //Icon(Icons.Filled.BikeScooter)
        // Image is a pre-defined composable that lays out and draws a given [ImageAsset].

        CoilImage(data = "https://electrek.co/wp-content/uploads/sites/3/2018/11/tesla-electric-bicycle-3.jpg")
        //Text("Alfred Sisley")
        //Text("3 minutes ago")
    }
}

/*SnackImage(
imageUrl = snack.imageUrl,
modifier = Modifier
.preferredSize(100.dp)
.constrainAs(image) {
    linkTo(
        top = parent.top,
        topMargin = 16.dp,
        bottom = parent.bottom,
        bottomMargin = 16.dp
    )
    linkTo(start = parent.start, end = name.start)
}
)*/

//Text("hi")
//CoilImage(data = "https://electrek.co/wp-content/uploads/sites/3/2018/11/tesla-electric-bicycle-3.jpg")


@Composable
fun BikeImage_old() {
    loadImageResource(id = R.drawable.fordbike).resource.resource?.let {
        Box(
            modifier = Modifier.clip(RoundedCornerShape(8.dp)),
            alignment = Alignment.BottomEnd
        ) {
            // Image is a pre-defined composable that lays out and draws a given [ImageAsset].
            Image(
                asset = it,
                modifier = Modifier.preferredHeight(70.dp)
            )
        }
    }
}

@Composable
fun colorGradiant() {
    VerticalGradient(
        listOf(Color.Red, Color.Green, Color.Blue),
        startY = 0.0f,
        endY = 100.0f
    )
}
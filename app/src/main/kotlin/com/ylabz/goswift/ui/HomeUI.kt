package com.ylabz.goswift.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.ui.tooling.preview.Preview
import com.ylabz.goswift.R
import com.ylabz.goswift.ui.components.JustMapUI
import com.ylabz.goswift.ui.utils.*
import dev.chrisbanes.accompanist.coil.CoilImage


// https://github.com/lelandrichardson/compose-dogfooding/blob/main/app/src/main/java/com/example/dogfood/MovieCarousel.kt
// JMV / JS / Koltin Mutli-platform does not have these issues.


@Composable
fun HomeUI() {


    // private var in class
    val count = remember { mutableStateOf(0) }

    Stack {
        // Map Background
        JustMapUI(latitude = 37.773972, longitude = -122.431297)
        // Info forground
        //Column {
        //    ExtendedFloatingActionButtonDemo()
        BikeInfoBox()
        //}
    }
}

@Composable
fun Chip(
    label: String,
    modifier: Modifier = Modifier.padding(horizontal = 10.dp),
    //border = BorderStroke(5.dp, Color.Red)
) {
    Text(
        label,
        fontSize = 12.sp,
        color = Color.Gray,
        //border =  BorderStroke(5.dp, Color.Blue),
        modifier = modifier.border(1.dp, Color.Gray, RoundedCornerShape(50))
            .padding(horizontal = 8.dp, vertical = 2.dp)
    )
}

@Composable
fun colorGradiant() {
    VerticalGradient(
        listOf(Color.Red, Color.Green, Color.Blue),
        startY = 0.0f,
        endY = 100.0f
    )
}


@Composable
fun StarRating(fl: Float) {

}

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

@Composable
fun BikeInfoBox() {
    Box(
        modifier = Modifier.fillMaxSize()
            //.fillMaxHeight() // takes a percentage
            //.fillMaxWidth(),
            .padding(bottom = 52.dp),
        gravity = ContentGravity.Center,
        border = BorderStroke(5.dp, Color.Red)
    ) {
        // We used another Box composable to create a container that will have the shadow applied.
        Box(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
                .preferredHeight(250.dp)
                .padding(16.dp)
                .drawShadow(
                    elevation = 30.dp,
                    shape = RoundedCornerShape(8.dp)
                ),
            gravity = ContentGravity.Center,
            border = BorderStroke(5.dp, Color.Magenta)
        ) {
            BoxInfo()
        }
    }
}


@Composable
fun BoxInfo(
    modifier: Modifier = Modifier
) {
    val resources = ContextAmbient.current.resources

    val col1 = listOf(Rose4, Lavender3, Rose2, Lavender3, Rose4)
    val col2 = listOf(Ocean3, Shadow3)
    Card(
        modifier = modifier.fillMaxSize(),//.padding(8.dp)
        //.horizontalGradientBackground(col1)
        backgroundColor = Color.Cyan
        //shape = RoundedCornerShape(4.dp)
    ) {
        ConstraintLayout {
            val (title, subtitle, image, T2, chip) = createRefs()
            // Box to occupy the entire available height & width using Modifier.fillMaxSize().
            Text(
                "Title", style = TextStyle(
                    fontFamily = FontFamily.Serif, fontWeight =
                    FontWeight.W900, fontSize = 14.sp
                ), modifier = Modifier.constrainAs(title) {
                    // Constraint the left edge of title to the right edge of the image
                    // and add a margin of 16dp
                    start.linkTo(parent.start, margin = 16.dp)
                    // Constraint the top edge of title to the top edge of the image
                    //top.linkTo(image.top)
                }
            )
            Text(
                "Subtitle", style = TextStyle(
                    fontFamily = FontFamily.Serif, fontWeight =
                    FontWeight.W900, fontSize = 14.sp
                ), modifier = Modifier.constrainAs(subtitle) {
                    // Constraint the bottom edge of subtitle to the bottom edge of the image
                    //bottom.linkTo(image.bottom)
                    // Constraint the start/left edge of subtitle to the right/end edge of the
                    // image and add a margin on 16.dp
                    end.linkTo(parent.end, margin = 16.dp)
                    bottom.linkTo(parent.bottom, margin = 70.dp)
                }
            )
            Text(
                "T2", style = TextStyle(
                    fontFamily = FontFamily.Serif, fontWeight =
                    FontWeight.W900, fontSize = 14.sp
                ), modifier = Modifier.constrainAs(T2) {
                    // Constraint the bottom edge of subtitle to the bottom edge of the image
                    //bottom.linkTo(image.bottom)
                    // Constraint the start/left edge of subtitle to the right/end edge of the
                    // image and add a margin on 16.dp
                    end.linkTo(parent.end, margin = 16.dp)
                }
            )
            BikeImage(
                modifier = Modifier
                    .preferredSize(140.dp)
                    //.fillMaxSize()
                    .constrainAs(image) {
                        bottom.linkTo(chip.top, margin = 1.dp)
                    }
            )


            // Place Switch here


            Row(modifier = Modifier.padding(4.dp)
                .constrainAs(chip) {
                    bottom.linkTo(parent.bottom, margin = 1.dp)
                }
            ) {
                // Chip (stringResource(R.string.app_name) // localization
                Chip("  Rent  ")
                Chip("  info  ")
            }
            /*Box(
        modifier = Modifier.preferredHeight(72.dp)
            .preferredWidth(72.dp)
            .constrainAs(image) {
                // We want to vertically center the image tag
                //centerVerticallyTo(parent)
                // Constraint the left edge of image to the left edge of the parent
                // and add a margin of 16dp
                //start.linkTo(parent.start, margin = 16.dp)
            }
    ) {
        // Image is a pre-defined composable that lays out and draws a given [ImageAsset].
        Image(imageFromResource(resources, R.drawable.fordbike))
    }*/
        }
    }
}


@Composable
fun BoxInfoHold() {
    ConstraintLayout {

        // setup constraint refs
        val (title, chip, image) = createRefs()
        Box(
            //modifier = Modifier.fillMaxSize(),
            //.fillMaxHeight() // takes a percentage
            //.fillMaxWidth(),
            //.padding(bottom = 52.dp),
            //gravity = ContentGravity.BottomStart,
            border = BorderStroke(5.dp, Color.Red)
        ) {

            Text(
                "Bike Title", style = TextStyle(
                    fontFamily = FontFamily.Serif, fontWeight =
                    FontWeight.W900, fontSize = 14.sp
                ), modifier = Modifier
                    //.fillMaxSize()
                    .constrainAs(title)
                    {
                        bottom.linkTo(parent.bottom, margin = 1.dp)
                        // Constraint the left edge of title to the right edge of the image
                        // and add a margin of 16dp
                        //start.linkTo(image.end, margin = 16.dp)
                        // Constraint the top edge of title to the top edge of the image
                        //top.linkTo(image.top)
                    }
            )
            BikeImage(
                modifier = Modifier
                    .preferredSize(140.dp)
                    //.fillMaxSize()
                    .constrainAs(image) {
                        start.linkTo(title.end, margin = 1.dp)
                    }
            )

            Row(modifier = Modifier.padding(4.dp).fillMaxSize()
                .constrainAs(chip) {
                    start.linkTo(title.start, margin = 1.dp)
                }
            ) {
                // Chip (stringResource(R.string.app_name) // localization
                Chip("  Rent  ")
                Chip("  info  ")
            }
        }
    }
}

@Composable
fun BikeImage(modifier: Modifier) {

    //Box(modifier = Modifier.clip(RoundedCornerShape(18.dp))) {
    Column {
        Text(
            "bike",
            style = typography.h6
        )
        Spacer(Modifier.preferredHeight(16.dp))
        Image(
            modifier = modifier,//.preferredSize(70.dp) ,
            //fill the width of the column, cropped appropriate height.
            contentScale = ContentScale.Inside,
            asset = imageResource(R.drawable.fordbike),
        )
    }
    //}
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

@Composable
fun BikeInfoCard() {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.surface,
    ) {
        Box(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
                .preferredHeight(250.dp)
                .padding(16.dp)
                .drawShadow(
                    elevation = 30.dp,
                    shape = RoundedCornerShape(8.dp)
                ),
            gravity = ContentGravity.Center,
            backgroundColor = Color.Cyan
        ) {
            Text(
                text = "This is a card view",
                style = MaterialTheme.typography.h4
            )
        }
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
            modifier = Modifier.clip(RoundedCornerShape(8.dp))
        ) {
            // Image is a pre-defined composable that lays out and draws a given [ImageAsset].
            Image(
                asset = it,
                modifier = Modifier.preferredHeight(70.dp)
            )
        }
    }
}

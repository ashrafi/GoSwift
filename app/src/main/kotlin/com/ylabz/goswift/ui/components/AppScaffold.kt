package com.ylabz.goswift.ui.components

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.InnerPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BikeScooter
import androidx.compose.material.icons.filled.Today
import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.Preview
import com.ylabz.goswift.ui.utils.NavScreen
import com.ylabz.goswift.ui.utils.navigateTo

@Composable
fun ScaffoldWithBottomBarAndCutout(bodyContent: @Composable() (InnerPadding) -> Unit) {
    // Consider negative values to mean 'cut corner' and positive values to mean 'round corner'
    val fabShape = RoundedCornerShape(50)
    // Scaffold is a pre-defined composable that implements the basic material design visual
    // layout structure. It takes in child composables for all the common elements that you see
    // in an app using material design - app bar, bottom app bar, floating action button, etc. It
    // also takes care of laying out these child composables in the correct positions - eg bottom
    // app bar is automatically placed at the bottom of the screen even though I didn't specify
    // that explicitly.
    Scaffold(
        topBar = { TopAppBar(title = { Text("Scaffold Examples") }) },
        bottomBar = {
            // We specify the shape of the FAB bu passing a shape composable (fabShape) as a
            // parameter to cutoutShape property of the BottomAppBar. It automatically creates a
            // cutout in the BottomAppBar based on the shape of the Floating Action Button.
            BottomAppBar(cutoutShape = fabShape) {
                bottomBar()
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateTo(NavScreen.Bike) },//XXX got o new palce to add value},
                // We specify the same shape that we passed as the cutoutShape above.
                shape = fabShape,
                // We use the secondary color from the current theme. It uses the defaults when
                // you don't specify a theme (this example doesn't specify a theme either hence
                // it will just use defaults. Look at DarkModeActivity if you want to see an
                // example of using themes.
                backgroundColor = MaterialTheme.colors.secondary
            ) {
                IconButton(onClick = { navigateTo(NavScreen.AddToGo) }) {
                    Icon(asset = Icons.Filled.Add)
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        bodyContent = bodyContent
    )
}

@Composable
fun bottomBar() {
    BottomAppBar {
        IconButton(onClick = {
            navigateTo(NavScreen.Bike)
        }) {
            Icon(Icons.Filled.BikeScooter)
        }
        IconButton(onClick = {
            navigateTo(NavScreen.Bike)
        }) {
            Icon(Icons.Filled.Today)
        }
    }
}

@Composable
fun testBody(padding: InnerPadding) : @Composable() (InnerPadding) -> Unit = {
    Column() {
        Text("Hello")
    }
}


@Preview
@Composable
fun PreviewScaffoldWithBottomBarAndCutout() {
    ScaffoldWithBottomBarAndCutout(testBody(InnerPadding()))
}

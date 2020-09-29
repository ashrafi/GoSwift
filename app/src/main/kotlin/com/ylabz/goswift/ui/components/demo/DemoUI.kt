package com.ylabz.goswift.ui.components.demo

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.ylabz.goswift.ui.MainComposeUI
import com.ylabz.goswift.ui.components.JustMapUI
import com.ylabz.goswift.ui.components.colors
import com.ylabz.goswift.ui.components.horizontalGradientBackground
import com.ylabz.goswift.ui.utils.*

@Composable
fun DemoUIView() {
    Stack {
        // 37.773972, -122.431297
        JustMapUI(latitude = 37.773972, longitude = -122.431297)
        Column {
            //ArtistCard()
            Row {
                //CustomViewComponent()
                ExtendedFloatingActionButtonDemo()
            }

            ConstraintLayout {
                // setup constraint refs
                val (title, subtitle, image) = createRefs()
                Text(
                    "Title", style = TextStyle(
                        fontFamily = FontFamily.Serif, fontWeight =
                        FontWeight.W900, fontSize = 14.sp
                    ), modifier = Modifier.constrainAs(title)
                    {
                        // Constraint the left edge of title to the right edge of the image
                        // and add a margin of 16dp
                        start.linkTo(image.end, margin = 16.dp)
                        // Constraint the top edge of title to the top edge of the image
                        top.linkTo(image.top)
                    }
                )
            }
        }
    }
}

@Composable
fun ExtendedFloatingActionButtonDemo() {
    ExtendedFloatingActionButton(
        icon = { Icon(Icons.Filled.Favorite) },
        text = { Text("") },
        onClick = { /*do something*/ },
        elevation = 8.dp
    )
}

@Composable
fun CustomViewComponent() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawCircle(
            color = Color.Red,
            radius = 300f
        )

        drawCircle(
            color = Color.Green,
            radius = 200f
        )

        drawCircle(
            color = Color.Blue,
            radius = 100f
        )
    }
}

@Composable
fun GreetingHold(name: String) {
    Text(text = "Hello $name!")
}

@Composable
private fun mainWin(mainComposeUI: MainComposeUI) {
    Crossfade(AppScreen.currentScreen) { screen ->
        Surface(color = MaterialTheme.colors.background) {

            /*backButtonHandler(onBackPressed = {
                navigateTo(
                    NavScreen.Home
                )
            })
            when (screen) {
                is NavScreen.Home -> mainComposeUI.Greeting("main")
                is NavScreen.Bike -> bikeComposeUI.Greeting("Bike")
                is NavScreen.AddToGo -> toGoView()
            }*/
        }
    }
}

private fun bodyContent(
    mainComposeUI: MainComposeUI,
    padding: PaddingValues
): @Composable() (PaddingValues) -> Unit = { padding ->
    // ScrollableColumn is a composable that adds the ability to scroll through the
    // child views
    ScrollableColumn {
        Column {
            mainWin(mainComposeUI)
        }
    }
}

private fun bodyContentScroll(
    padding: PaddingValues = PaddingValues()
): @Composable() (PaddingValues) -> Unit = {
    ScrollableColumn(contentPadding = PaddingValues()) {
        Column {
            repeat(100) {
                // Card composable is a predefined composable that is meant to represent
                // the card surface as specified by the Material Design specification. We
                // also configure it to have rounded corners and apply a modifier.
                Card(
                    backgroundColor = colors[it % colors.size],
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Spacer(modifier = Modifier.fillMaxWidth().preferredHeight(200.dp))
                }
            }
        }
    }
}

@Composable
private fun AppContent(
    mainComposeUI: MainComposeUI
) {
    Column {
        //BottomNavigationOnlySelectedLabelComponent()
        //ScaffoldWithBottomBarAndCutout(bodyContent(mainComposeUI, bikeComposeUI, InnerPadding()))
    }

}

@Composable
fun grad(gradColors: List<Color>) {
    Box {
        Text("hi")
        Spacer(
            modifier = Modifier
                .preferredHeight(280.dp)
                .fillMaxWidth()
                .horizontalGradientBackground(gradColors)
        )
        Text("second 2")
    }
}

@Composable
fun gradBase() {
    Box {
        Text("hi")
        Spacer(
            modifier = Modifier
                .preferredHeight(280.dp)
                .fillMaxWidth()
                .background(Rose4)
        )
        Text("second 2")
    }
}

val grad_color_rose = listOf(Rose4, Lavender3, Rose2, Lavender3, Rose4)
val grad_color_blue = listOf(Ocean3, Shadow3)
val grad_red_blue = listOf(Rose4, Ocean3)

@Preview(name = "base")
@Composable
fun PreviewDemoUIc() {
    grad(grad_color_rose)
    //ScaffoldWithBottomBarAndCutout { Text("hi") }
    //DemoUIView()
}


@Preview(name = "Rose")
@Composable
fun PreviewDemoUIr() {
    grad(grad_color_rose)
    //ScaffoldWithBottomBarAndCutout { Text("hi") }
    //DemoUIView()
}


@Preview(name = "Blue")
@Composable
fun PreviewDemoUIb() {
    grad(grad_color_blue)
    //ScaffoldWithBottomBarAndCutout { Text("hi") }
    //DemoUIView()
}

@Preview(name = "Red/Blue")
@Composable
fun PreviewDemoUIbr() {
    grad(grad_red_blue)
    //ScaffoldWithBottomBarAndCutout { Text("hi") }
    //DemoUIView()
}


@Preview(name = "Scroll Red/Blue")
@Composable
fun PreviewbodyContentScrollDemoUIbr() {
    bodyContentScroll()
}

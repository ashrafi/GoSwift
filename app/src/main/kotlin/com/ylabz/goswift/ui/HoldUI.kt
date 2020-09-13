package com.ylabz.goswift.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.ylabz.goswift.ui.components.colors
import com.ylabz.goswift.ui.utils.AppScreen
import com.ylabz.goswift.ui.utils.GoSwiftTheme


@Composable
fun GreetingHold(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GoSwiftTheme {
        GreetingHold("Android")
    }
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
    padding: InnerPadding
): @Composable() (InnerPadding) -> Unit = { padding ->
    // ScrollableColumn is a composable that adds the ability to scroll through the
    // child views
    ScrollableColumn {
        Column {
            mainWin(mainComposeUI)
        }
    }
}

private fun bodyContentScroll(padding: InnerPadding): @Composable() (InnerPadding) -> Unit =
    { padding ->
        // ScrollableColumn is a composable that adds the ability to scroll through the
        // child views
        ScrollableColumn {
            // Column is a composable that places its children in a vertical sequence. You
            // can think of it similar to a LinearLayout with the vertical orientation.
            Column(Modifier.padding(padding)) {
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
package com.ylabz.goswift.ui

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainComposeUI {
    @Composable
    fun Greeting(name: String) {
        Column {
            Text (text = "Hello From Main $name!")
            HomeScreen()
        }
    }

    @Composable
    fun HomeScreen() {
        SimpleButtonComponentHome()
        //val wordViewModel = viewModel<WordViewModel>()
        //val currState: ViewState by wordViewModel.state.collectAsState(initial = ViewState())
        //CurrAppState.provides(currState)
        /*Providers(CurrAppState provides currState) {
            ScaffoldWithBottomBarAndCutout(wordViewModel)
        }*/

    }

    // We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
    @Composable
    fun SimpleButtonComponentHome() {
        // Button is a pre-defined Material Design implementation of a contained button -
        // https://material.io/design/components/buttons.html#contained-button.

        // You can think of Modifiers as implementations of the decorators pattern that are used to
        // modify the composable that its applied to. In this example, we assign a padding of
        // 16dp to the Button.
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
            Text(text = "Nav to Bike", modifier = Modifier.padding(16.dp))
        }
    }

}
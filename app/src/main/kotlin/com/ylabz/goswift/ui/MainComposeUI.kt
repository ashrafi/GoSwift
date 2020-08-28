package com.ylabz.goswift.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.graphics.asImageAsset
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import coil.Coil
import coil.request.GetRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainComposeUI {
    @Composable
    fun Greeting(name: String) {
        Column {
            Text (text = "Hello From Main $name!")
            showImage()
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

        Button(
                modifier = Modifier.padding(16.dp),
                elevation = 5.dp,
                onClick = {

                }) {
            // The Button composable allows you to provide child composables that inherit this button
            // functionality.
            // The Text composable is pre-defined by the Compose UI library; you can use this
            // composable to render text on the screen
            Text(text = "Call API", modifier = Modifier.padding(16.dp))
        }
    }

    @Composable
    fun showImage() {
        //val image = state<ImageAsset> { ImageAsset(24, 24) }
        val image = remember { mutableStateOf(ImageAsset(24,24)) }
        val image_data =
                "https://www.sail-world.com/photos/sailworld/photos/Large_610577174_SD5_3584.jpg"

        val request = GetRequest.Builder(ContextAmbient.current)
                .data(image_data)
                //.transformations(CircleCropTransformation())
                .build()

        CoroutineScope(Dispatchers.Main.immediate).launch {
            // Start loading the image and await the result.
            val drawable = Coil.execute(request).drawable
            image.value = drawable?.toBitmap(
                    width = drawable.intrinsicWidth,
                    height = drawable.intrinsicHeight
            )!!.asImageAsset()
        }

        Image(asset = image.value)

    }

}
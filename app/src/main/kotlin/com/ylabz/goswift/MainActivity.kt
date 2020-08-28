package com.ylabz.goswift

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import com.ylabz.goswift.ui.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoSwiftTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        Greeting("Go Swift Bike 2")
                        AppContent(mainComposeUI = MainComposeUI(), bikeComposeUI = BikeComposeUI())
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GoSwiftTheme {
        Greeting("Android")
    }
}

@Composable
private fun AppContent(
    mainComposeUI: MainComposeUI,
    bikeComposeUI: BikeComposeUI
) {
    Crossfade(AppScreen.currentScreen) { screen ->
        Surface(color = MaterialTheme.colors.background) {
            /*backButtonHandler(onBackPressed = {
                navigateTo(
                    NavScreen.Home
                )
            })*/
            when (screen) {
                is NavScreen.Home -> mainComposeUI.Greeting("main")
                is NavScreen.Bike -> bikeComposeUI.Greeting("Bike")
            }
        }
    }
}
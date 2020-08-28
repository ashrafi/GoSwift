package com.ylabz.goswift.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


// Navigation
/**
 * Class defining the screens we have in the app: home, article details and interests
 */
sealed class NavScreen {
    object Home : NavScreen()
    object Bike : NavScreen()
}

object AppScreen {
    var currentScreen by mutableStateOf<NavScreen>(
        NavScreen.Home
    )
}

/**
 * Temporary solution pending navigation support.
 */
fun navigateTo(destination: NavScreen) {
    AppScreen.currentScreen = destination
}

// back nav
// https://gist.github.com/adamp/62d13fe5bf0d6ddf9fcf58f8a6769523
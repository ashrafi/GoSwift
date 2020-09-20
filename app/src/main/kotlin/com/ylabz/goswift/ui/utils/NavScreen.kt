package com.ylabz.goswift.ui.utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle


// Navigation
/**
 * Class defining the screens we have in the app: home, article details and interests
 */
sealed class NavScreen {
    object Home : NavScreen()
    object Bike : NavScreen()
    object BikeDetail : NavScreen()
    object GoToAdd : NavScreen()
    object GoToList : NavScreen()
    object Map : NavScreen()
}

/**
 * Convert a screen to a bundle that can be stored in [SavedStateHandle]
 *
private fun Screen.toBundle(): Bundle {
    return bundleOf(SIS_NAME to id.name).also {
        // add extra keys for various types here
        if (this is Article) {
            it.putString(SIS_POST, postId)
        }
    }
}*/

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
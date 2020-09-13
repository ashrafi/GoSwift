package com.ylabz.goswift

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import com.ylabz.goswift.ui.HomeUI
import com.ylabz.goswift.ui.MapUI
import com.ylabz.goswift.ui.bikeInfo
import com.ylabz.goswift.ui.components.ScaffoldWithBottomBarAndCutout
import com.ylabz.goswift.ui.goToEvnt
import com.ylabz.goswift.ui.utils.AppScreen
import com.ylabz.goswift.ui.utils.NavScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    /*@Inject
    lateinit var mainComposeUI: MainComposeUI*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainUI()
        }
    }

    // DetailsScreen(DetailsActivityArg("ash", "code", "37.7749", "-122.4194"))
    @Composable
    fun MainUI() {
        ScaffoldWithBottomBarAndCutout({
            Crossfade(AppScreen.currentScreen) { screen ->
                Surface(color = MaterialTheme.colors.background) {
                    /*backButtonHandler(onBackPressed = {
                        navigateTo(
                            NavScreen.Home
                        )
                    })*/
                    when (screen) {
                        is NavScreen.Home -> HomeUI()
                        is NavScreen.Bike -> bikeInfo("Bike")
                        is NavScreen.AddToGo -> goToEvnt()
                        is NavScreen.Map -> MapUI()
                    }
                }
            }
        })
    }

    @Preview
    @Composable
    fun PreviewMainComposeUI() {
        MainUI()
    }

}

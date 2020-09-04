package com.ylabz.goswift.ui.components

// need to always add
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.InnerPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


val colors = listOf(
    Color(0xFFffd7d7.toInt()),
    Color(0xFFffe9d6.toInt()),
    Color(0xFFfffbd0.toInt()),
    Color(0xFFe3ffd9.toInt()),
    Color(0xFFd0fff8.toInt())
)

private val listItems = listOf("Games", "Apps", "Movies", "Books")


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
            BottomAppBar(cutoutShape = fabShape) {}
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                // We specify the same shape that we passed as the cutoutShape above.
                shape = fabShape,
                // We use the secondary color from the current theme. It uses the defaults when
                // you don't specify a theme (this example doesn't specify a theme either hence
                // it will just use defaults. Look at DarkModeActivity if you want to see an
                // example of using themes.
                backgroundColor = MaterialTheme.colors.secondary
            ) {
                IconButton(onClick = {}) {
                    Icon(asset = Icons.Filled.Favorite)
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        bodyContent = bodyContent
    )
}

@Composable
fun BottomNavigationOnlySelectedLabelComponent() {
    // Reacting to state changes is the core behavior of Compose. We use the state composable
    // that is used for holding a state value in this composable for representing the current
    // value of the selectedIndex. Any composable that reads the value of counter will be recomposed
    // any time the value changes. This ensures that only the composables that depend on this
    // will be redraw while the rest remain unchanged. This ensures efficiency and is a
    // performance optimization. It is inspired from existing frameworks like React.
    var selectedIndex by remember { mutableStateOf(0) }
    // BottomNavigation is a component placed at the bottom of the screen that represents primary
    // destinations in your application.
    BottomNavigation(modifier = Modifier.padding(16.dp)) {
        listItems.forEachIndexed { index, label ->
            // A composable typically used as part of BottomNavigation. Since BottomNavigation
            // is usually used to represent primary destinations in your application,
            // BottomNavigationItem represents a singular primary destination in your application.
            BottomNavigationItem(
                icon = {
                    // Simple composable that allows you to draw an icon on the screen. It
                    // accepts a vector asset as the icon.
                    Icon(asset = Icons.Filled.Favorite)
                },
                label = {
                    // Text is a predefined composable that does exactly what you'd expect it to -
                    // display text on the screen. It allows you to customize its appearance using the
                    // style property.
                    Text(text = label)
                },
                selected = selectedIndex == index,
                // Update the selected index when the BottomNavigationItem is clicked
                onSelect = { selectedIndex = index },
                // Setting this to false causes the label to be show only for the navigation item
                // that is currently selected, like in the BottomNavigationAlwaysShowLabelComponent
                // component.
                alwaysShowLabels = false
            )
        }
    }
}
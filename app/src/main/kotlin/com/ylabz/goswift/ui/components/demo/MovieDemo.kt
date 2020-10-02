package com.ylabz.goswift.ui.components.demo

import androidx.compose.animation.asDisposableClock
import androidx.compose.animation.core.TargetAnimation
import androidx.compose.foundation.Text
import androidx.compose.foundation.animation.FlingConfig
import androidx.compose.foundation.animation.defaultFlingConfig
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableController
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.LayoutModifier
import androidx.compose.ui.Measurable
import androidx.compose.ui.MeasureScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AnimationClockAmbient
import androidx.compose.ui.platform.ConfigurationAmbient
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlin.math.abs
import kotlin.math.roundToInt

data class Movie(
    val title: String,
    val posterUrl: String,
    val bgUrl: String,
    val color: Color,
    val chips: List<String>
)

val movies = listOf(
    Movie(
        title = "Good Boys",
        posterUrl = "https://m.media-amazon.com/images/M/MV5BMTc1NjIzODAxMF5BMl5BanBnXkFtZTgwMTgzNzk1NzM@._V1_.jpg",
        bgUrl = "https://m.media-amazon.com/images/M/MV5BMTc1NjIzODAxMF5BMl5BanBnXkFtZTgwMTgzNzk1NzM@._V1_.jpg",
        color = Color.Red,
        chips = listOf("Action", "Drama", "History")
    ),
    Movie(
        title = "Joker",
        posterUrl = "https://i.etsystatic.com/15963200/r/il/25182b/2045311689/il_794xN.2045311689_7m2o.jpg",
        bgUrl = "https://images-na.ssl-images-amazon.com/images/I/61gtGlalRvL._AC_SY741_.jpg",
        color = Color.Blue,
        chips = listOf("Action", "Drama", "History")
    ),
    Movie(
        title = "The Hustle",
        posterUrl = "https://m.media-amazon.com/images/M/MV5BMTc3MDcyNzE5N15BMl5BanBnXkFtZTgwNzE2MDE0NzM@._V1_.jpg",
        bgUrl = "https://m.media-amazon.com/images/M/MV5BMTc3MDcyNzE5N15BMl5BanBnXkFtZTgwNzE2MDE0NzM@._V1_.jpg",
        color = Color.Yellow,
        chips = listOf("Action", "Drama", "History")
    )
)

@Composable
fun Screen() {
    val configuration = ConfigurationAmbient.current
    val density = DensityAmbient.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenWidthPx = with(density) { screenWidth.toPx() }
    val screenHeight = configuration.screenHeightDp.dp
    val screenHeightPx = with(density) { screenHeight.toPx() }


    // offest for
    var offset by remember { mutableStateOf(0f) }


    val posterWidthDp = screenWidth * 0.6f
    val posterSpacingPx = with(density) { posterWidthDp.toPx() + 20.dp.toPx() }
    val indexFraction = -1 * offset / posterSpacingPx
    val flingConfig = defaultFlingConfig {
        TargetAnimation((it / posterSpacingPx).roundToInt() * posterSpacingPx)
    }
    val upperBound = 0f
    val lowerBound = -1 * (movies.size - 1) * posterSpacingPx


    val ctrlr = rememberScrollableController(flingConfig) {
        val target = offset + it
        when {
            target > upperBound -> {
                val consumed = upperBound - offset
                offset = upperBound
                consumed
            }
            target < lowerBound -> {
                val consumed = lowerBound - offset
                offset = lowerBound
                consumed
            }
            else -> {
                offset = target
                it
            }
        }
    }

    fun lerp(start: Float, stop: Float, fraction: Float): Float {
        return (1 - fraction) * start + fraction * stop
    }

    movies.forEachIndexed { index, movie ->
        val center = posterSpacingPx * index
        val distFromCenter = abs(offset + center) / posterSpacingPx
        MoviePoster(
            movie = movie,
            modifier = Modifier
                .offset(getX = { center + offset }, getY = { lerp(0f, 50f, distFromCenter) })
                .width(posterWidthDp)
        )
    }
}

val posterAspectRatio = .674f

@Composable
fun rememberScrollableController(
    flingConfig: FlingConfig = defaultFlingConfig(),
    consumeScrollDelta: (Float) -> Float
): ScrollableController {
    val clocks = AnimationClockAmbient.current.asDisposableClock()
    return remember(clocks, flingConfig) {
        ScrollableController(consumeScrollDelta, flingConfig, clocks)
    }
}

@Composable
fun MoviePoster(movie: Movie, modifier: Modifier = Modifier) {
    Column(
        modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .padding(20.dp)
    ) {
        CoilImage(
            movie.posterUrl,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(posterAspectRatio)
                .clip(RoundedCornerShape(10.dp))
        )
        Text(
            movie.title,
            fontSize = 24.sp,
            color = Color.Black
        )
    }
}

fun Modifier.offset(
    getX: () -> Float,
    getY: () -> Float,
    rtlAware: Boolean = true
) = this then object : LayoutModifier {
    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureScope.MeasureResult {
        val placeable = measurable.measure(constraints)
        return layout(placeable.width, placeable.height) {
            if (rtlAware) {
                placeable.placeRelative(getX().roundToInt(), getY().roundToInt())
            } else {
                placeable.place(getX().roundToInt(), getY().roundToInt())
            }
        }
    }
}
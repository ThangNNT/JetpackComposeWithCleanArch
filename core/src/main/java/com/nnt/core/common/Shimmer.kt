package com.nnt.core.common

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.nnt.jetpackcomposewithcleanarch.ui.theme.ShimmerColorShades

/**
 * Thanks [https://github.com/sunny52525/GFG-articles/tree/master/Shimmer%20Animation] for the code below.
 */
@Composable
fun MovieShimmerItem(
    brush: Brush, modifier: Modifier = Modifier
        .padding(10.dp, 0.dp)
        .width(MovieCardConfig.CARD_WIDTH)
        .height(MovieCardConfig.CARD_HEIGHT)
) {
    // Column composable containing spacer shaped like a rectangle,
    // set the [background]'s [brush] with the brush receiving from [ShimmerAnimation]
    // Composable which is the Animation you are gonna create.
    Column {
        Spacer(
            modifier = modifier.background(brush)
        )
    }
}

@Composable
fun HorizontalMovieShimmerItem(
    brush: Brush, modifier: Modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
        .height(MovieCardConfig.CARD_HEIGHT)
) {
    // Column composable containing spacer shaped like a rectangle,
    // set the [background]'s [brush] with the brush receiving from [ShimmerAnimation]
    // Composable which is the Animation you are gonna create.
    Column {
        Spacer(
            modifier = modifier.background(brush)
        )
    }
}

@Composable
fun ShimmerMovieCardLoadingAnimation(movieShimmerCardType: MovieShimmerCardType, modifier: Modifier? = null) {

    /*
     Create InfiniteTransition
     which holds child animation like [Transition]
     animations start running as soon as they enter
     the composition and do not stop unless they are removed
    */
    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(
        /*
         Specify animation positions,
         initial Values 0F means it
         starts from 0 position
        */
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            // Tween Animates between values over specified [durationMillis]
            tween(durationMillis = 1200, easing = FastOutSlowInEasing),
            RepeatMode.Reverse
        )
    )

    /*
      Create a gradient using the list of colors
      Use Linear Gradient for animating in any direction according to requirement
      start=specifies the position to start with in cartesian like system Offset(10f,10f) means x(10,0) , y(0,10)
      end = Animate the end position to give the shimmer effect using the transition created above
    */
    val brush = Brush.linearGradient(
        colors = ShimmerColorShades,
        start = Offset(10f, 10f),
        end = Offset(translateAnim, translateAnim)
    )
    if(movieShimmerCardType == MovieShimmerCardType.Horizontal)
        if(modifier == null){
            HorizontalMovieShimmerItem(brush = brush)

        }
        else {
            HorizontalMovieShimmerItem(brush = brush, modifier = modifier)
        }
    else {
        if(modifier == null){
            MovieShimmerItem(brush = brush)
        }
        else {
            MovieShimmerItem(brush = brush, modifier = modifier)
        }
    }
}

enum class MovieShimmerCardType {
    Normal,
    Horizontal
}
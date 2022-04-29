package com.nnt.core.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.nnt.core.R

@Composable
fun RateBar(ratePercent: Float, modifier: Modifier = Modifier) {
    val rate = ratePercent*5f
    val fullStarId = R.drawable.ic_star
    val halfStarId = R.drawable.ic_star_half
    val emptyStarId = R.drawable.ic_star_border
    val rateImageIds = ArrayList<Int>()
    for(index in 1 until 6){
        if(rate - index.toFloat()>=0f){
            rateImageIds.add(fullStarId)
        }
        else if(rate - index.toFloat()>-1f){
            rateImageIds.add(halfStarId)
        }
        else if(rate - index.toFloat()<=-1f){
            break
        }
        else {
            rateImageIds.add(emptyStarId)
        }
    }
    Row(modifier = modifier) {
        rateImageIds.forEachIndexed { index, _ ->
            Image(painter = painterResource(id = rateImageIds[index]), contentDescription = "star")
        }
    }
}
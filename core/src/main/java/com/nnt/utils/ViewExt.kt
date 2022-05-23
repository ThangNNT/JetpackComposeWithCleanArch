package com.nnt.utils

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

@SuppressLint("UnnecessaryComposedModifier")
inline fun Modifier.singleClickable(crossinline onClick: ()-> Unit): Modifier = composed {
    var clickTime = remember {
        mutableStateOf(0L)
    }
    clickable {
        val currentTime = System.currentTimeMillis()
        if ((currentTime - clickTime.value) < 1000L) return@clickable
        clickTime = mutableStateOf(currentTime)
        onClick()
    }
}
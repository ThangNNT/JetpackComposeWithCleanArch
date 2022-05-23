package com.nnt.utils

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

@SuppressLint("UnnecessaryComposedModifier")
inline fun Modifier.singleClickable(crossinline onClick: ()-> Unit): Modifier = composed {
    val previousClickTime = remember {
        mutableStateOf(0L)
    }
    val isClicking = remember {
        mutableStateOf(false)
    }
    clickable {
        isClicking.value = true
        val currentTime = System.currentTimeMillis()
        if ((currentTime - previousClickTime.value) < 1000L || !isClicking.value) {
            return@clickable
        }
        isClicking.value = false
        previousClickTime.value = currentTime
        onClick()
    }
}
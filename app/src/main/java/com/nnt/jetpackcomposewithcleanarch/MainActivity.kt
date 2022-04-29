package com.nnt.jetpackcomposewithcleanarch

import android.os.Bundle
import androidx.compose.ui.unit.ExperimentalUnitApi

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import com.nnt.jetpackcomposewithcleanarch.ui.theme.JetpackComposeWithCleanArchTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @InternalCoroutinesApi
    @ExperimentalFoundationApi
    @OptIn(ExperimentalUnitApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeWithCleanArchTheme {
                JetApp()
            }
        }
    }
}

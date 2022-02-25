package com.nnt.navigator

import android.content.Context
import android.content.Intent

interface MovieDetailActivityNavigator: Navigator {
    fun newIntent(context: Context, movieId: Int): Intent
}
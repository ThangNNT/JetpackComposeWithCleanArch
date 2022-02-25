package com.nnt.remote.response

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class SpokenLanguage(
    val iso_639_1: String? = null,
    val name: String? = null
)
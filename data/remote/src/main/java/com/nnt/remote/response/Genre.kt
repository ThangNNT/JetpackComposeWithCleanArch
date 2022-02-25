package com.nnt.remote.response

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Genre(
    val id: Int? = null,
    val name: String? = null
)
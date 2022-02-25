package com.nnt.remote.response

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class ProductionCountry(
    val iso_3166_1: String? = null,
    val name: String? = null
)
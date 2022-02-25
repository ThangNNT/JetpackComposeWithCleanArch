package com.nnt.remote.response

import androidx.annotation.Keep
import kotlinx.serialization.Serializable


@Keep
@Serializable
data class ProductionCompany(
    val id: Int? = null,
    val logo_path: String? = null,
    val name: String? = null,
    val origin_country: String? = null
)
package com.nnt.utils


private const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"

fun buildImageUrl(url: String?): String? {
    url?.let { return "$BASE_IMAGE_URL$url" }
    return null
}

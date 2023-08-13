package com.sami.features.dashboard.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Media(
    @Json(name="media-metadata")
    val mediaMetadata: List<MediaMetadata>,
    @Json(name="type")
    val type: String
)
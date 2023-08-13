package com.sami.features.dashboard.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DashboardDataResponse(
    @Json(name="id")
    val id: Long,
    @Json(name="title")
    val title: String,
    @Json(name="byline")
    val description: String,
    @Json(name="media")
    val media: List<Media>,
    @Json(name="published_date")
    val publishedDate: String,
    @Json(name="url")
    val url: String
)
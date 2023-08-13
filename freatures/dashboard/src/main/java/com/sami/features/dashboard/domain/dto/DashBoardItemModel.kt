package com.sami.features.dashboard.domain.dto

data class DashBoardItemModel(
    val id: Long,
    val thumbnail: String?,
    val title: String,
    val date: String,
    val dateLong: Long,
    val description: String,
    val url: String,
)

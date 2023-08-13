package com.sami.features.dashboard.data.mapper

import com.sami.core.formatter.Formatter
import com.sami.core.formatter.di.FormatterModule.BindDateFormatter
import com.sami.core.formatter.di.FormatterModule.BindDateToLongFormatter
import com.sami.core.mapper.Mapper
import com.sami.features.dashboard.data.model.DashboardDataResponse
import com.sami.features.dashboard.domain.dto.DashBoardItemModel
import javax.inject.Inject

class DashboardModelMapper @Inject constructor(
    @BindDateFormatter private val dateFormatter: Formatter,
    @BindDateToLongFormatter private val dateToLongFormatter: Formatter,
) : Mapper<DashboardDataResponse, DashBoardItemModel>() {
    override fun map(source: DashboardDataResponse): DashBoardItemModel {
        val imageUrl =
            source.media.find { it.type == IMAGE_DATA_TYPE }?.mediaMetadata?.firstOrNull()?.url
        return DashBoardItemModel(
            id = source.id,
            title = source.title,
            date = dateFormatter.format(source.publishedDate),
            dateLong = dateToLongFormatter.format(source.publishedDate).toLong(),
            description = source.description,
            url = source.url,
            thumbnail = imageUrl
        )
    }

    companion object {
        const val IMAGE_DATA_TYPE = "image"
    }
}
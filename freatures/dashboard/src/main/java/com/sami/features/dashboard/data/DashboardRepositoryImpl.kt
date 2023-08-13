package com.sami.features.dashboard.data

import com.sami.features.dashboard.data.mapper.DashboardModelMapper
import com.sami.features.dashboard.data.remote.DashboardRemoteDataSource
import com.sami.features.dashboard.domain.DashboardRepository
import com.sami.features.dashboard.domain.dto.DashBoardItemModel
import javax.inject.Inject

class DashboardRepositoryImpl @Inject constructor(
    private val dataSource: DashboardRemoteDataSource,
    private val dashboardModelMapper: DashboardModelMapper
) : DashboardRepository {
    override suspend fun getDashboardData(): List<DashBoardItemModel> {
        return kotlin.runCatching { dataSource.getDashboardData() }
            .map(dashboardModelMapper::mapList)
            .getOrThrow()
    }
}
package com.sami.features.dashboard.domain

import com.sami.features.dashboard.domain.dto.DashBoardItemModel

interface DashboardRepository {
    suspend fun getDashboardData(): List<DashBoardItemModel>
}
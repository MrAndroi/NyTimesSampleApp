package com.sami.features.dashboard.domain.usecase

import com.sami.features.dashboard.domain.DashboardRepository
import com.sami.features.dashboard.domain.dto.DashBoardItemModel
import javax.inject.Inject

class GetDashBoardDataUseCase @Inject constructor(
    private val repository: DashboardRepository
) {
    suspend operator fun invoke(): List<DashBoardItemModel> {
        return repository.getDashboardData()
    }

}
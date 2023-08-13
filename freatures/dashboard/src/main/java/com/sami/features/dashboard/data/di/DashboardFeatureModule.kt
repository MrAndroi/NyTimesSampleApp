package com.sami.features.dashboard.data.di

import com.sami.features.dashboard.data.DashboardRepositoryImpl
import com.sami.features.dashboard.domain.DashboardRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DashboardFeatureModule {
    @Binds
    fun bindDashboardRepository(repo: DashboardRepositoryImpl): DashboardRepository
}
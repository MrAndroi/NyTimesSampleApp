package com.sami.features.dashboard.data.di

import com.sami.features.dashboard.data.remote.DashboardRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object DashboardRemoteModule {

    @Provides
    fun provideDashboardRemoteDataSource(retrofit: Retrofit): DashboardRemoteDataSource {
        return retrofit.create()
    }
}
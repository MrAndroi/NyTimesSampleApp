package com.sami.features.dashboard.data.remote

import com.sami.features.dashboard.data.model.DashboardDataResponse
import com.serjltt.moshi.adapters.Wrapped
import retrofit2.http.GET

interface DashboardRemoteDataSource {

    @GET("viewed/30.json")
    @Wrapped(path = ["results"])
    suspend fun getDashboardData(): List<DashboardDataResponse>
}
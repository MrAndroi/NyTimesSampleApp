package com.sami.core.datastore.usecase

import androidx.datastore.core.DataStore
import com.sami.core.datastore.models.AppDataModel
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetAppDataUseCase @Inject constructor(private val appData: DataStore<AppDataModel>) {

    suspend operator fun invoke(): AppDataModel {
        return appData.data.first()
    }
}
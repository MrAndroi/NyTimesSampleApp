package com.sami.core.datastore.usecase

import androidx.datastore.core.DataStore
import com.sami.core.datastore.models.AppDataModel
import com.sami.core.datastore.models.UserDataModel
import javax.inject.Inject

class ClearAppDataUseCase @Inject constructor(private val appData: DataStore<AppDataModel>) {

    suspend operator fun invoke(): AppDataModel {
        return appData.updateData { data ->
            data.copy(
                currentUser = null,
                users = emptySet<UserDataModel>().toMutableSet(),
            )
        }
    }
}
package com.sami.core.datastore.usecase

import androidx.datastore.core.DataStore
import com.sami.core.datastore.models.AppDataModel
import com.sami.core.datastore.models.UserDataModel
import javax.inject.Inject

class UpdateAppDataUseCase @Inject constructor(private val appData: DataStore<AppDataModel>) {
    suspend operator fun invoke(
        currentUser: UserDataModel?
    ) {
        appData.updateData { data ->
            val updatedUsers = data.users.toMutableSet()
            currentUser?.let { updatedUsers.add(it) }
            data.copy(
                currentUser = currentUser,
                users = updatedUsers
            )
        }
    }
}

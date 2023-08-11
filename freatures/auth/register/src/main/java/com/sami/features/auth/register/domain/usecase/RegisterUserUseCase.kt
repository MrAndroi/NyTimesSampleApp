package com.sami.features.auth.register.domain.usecase

import com.sami.core.datastore.models.UserDataModel
import com.sami.core.datastore.usecase.UpdateAppDataUseCase
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val checkIfUserExistUseCase: CheckIfUserExistUseCase,
    private val updateAppDataUseCase: UpdateAppDataUseCase,
) {
    suspend operator fun invoke(
        userDataModel: UserDataModel
    ): Boolean {
        val userAlreadyRegistered = checkIfUserExistUseCase(userDataModel.email)
        return if (userAlreadyRegistered) {
            false
        } else {
            updateAppDataUseCase(userDataModel)
            true
        }
    }
}
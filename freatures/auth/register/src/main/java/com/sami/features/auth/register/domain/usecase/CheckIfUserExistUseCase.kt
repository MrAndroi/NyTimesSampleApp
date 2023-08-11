package com.sami.features.auth.register.domain.usecase

import com.sami.core.datastore.usecase.GetAppDataUseCase
import javax.inject.Inject

class CheckIfUserExistUseCase @Inject constructor(
    private val getAppDataUseCase: GetAppDataUseCase
) {
    suspend operator fun invoke(email: String): Boolean {
        return getAppDataUseCase().users.firstOrNull { it.email == email } != null
    }
}
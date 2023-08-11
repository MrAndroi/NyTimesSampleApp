package com.sami.features.auth.login.domain.usecase

import com.sami.core.datastore.usecase.GetAppDataUseCase
import com.sami.core.datastore.usecase.UpdateAppDataUseCase
import com.sami.features.auth.login.domain.model.LoginErrors
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val updateAppDataUseCase: UpdateAppDataUseCase,
    private val getAppDataUseCase: GetAppDataUseCase
) {
    suspend operator fun invoke(
        email: String,
        password: String,
    ): LoginErrors {
        val user = getAppDataUseCase().users.firstOrNull { it.email == email }
        user?.let {
            if (it.password == password) {
                updateAppDataUseCase(currentUser = it)
                return LoginErrors.NO_ERROR
            } else {
                return LoginErrors.INVALID_PASSWORD
            }
        } ?: run {
            return LoginErrors.EMAIL_NOT_REGISTERED
        }
    }
}
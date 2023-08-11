package com.sami.core.validation.usecase

import javax.inject.Inject

class ValidateEmailUseCase @Inject constructor() {

    private val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$")
    internal operator fun invoke(email: String): Boolean {
        return email.matches(emailRegex)
    }
}
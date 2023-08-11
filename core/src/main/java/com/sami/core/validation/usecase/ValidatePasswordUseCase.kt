package com.sami.core.validation.usecase

import javax.inject.Inject

class ValidatePasswordUseCase @Inject constructor() {

    private val passwordRegex = Regex("^(?=.*[A-Z])(?=.*\\d).{8,}$")
    internal operator fun invoke(password: String): Boolean {
        return passwordRegex.matches(password)
    }
}
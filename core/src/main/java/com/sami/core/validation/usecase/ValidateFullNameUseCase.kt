package com.sami.core.validation.usecase

import javax.inject.Inject

class ValidateFullNameUseCase @Inject constructor() {

    private val fullNameRegex = Regex("^[a-zA-Z]{4,}\\s[a-zA-Z]{4,}$")
    internal operator fun invoke(fullName: String): Boolean {
        return fullNameRegex.matches(fullName)
    }
}
package com.sami.core.validation.usecase

import javax.inject.Inject

class ValidateDateOfBirthUseCase @Inject constructor() {

    private val dateOfBirthRegex = Regex("^(?:(?:0[1-9]|1[0-2])/(?:0[1-9]|[12][0-9]|3[01])|(?:0[1-9]|[12][0-9]|3[01])/(?:0[1-9]|1[0-2]))/\\d{4}$")
    internal operator fun invoke(dateOfBirth: String): Boolean {
        return dateOfBirthRegex.matches(dateOfBirth)
    }
}
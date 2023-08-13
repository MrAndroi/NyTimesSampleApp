package com.sami.core.validation.usecase

import javax.inject.Inject

class ValidatePhoneNumberUseCase @Inject constructor() {

    private val phoneNumberRegex = Regex("^(\\+9627[789])\\d{7}$")

    operator fun invoke(phoneNumber: String): Boolean {
        return phoneNumberRegex.matches("+962$phoneNumber")
    }
}
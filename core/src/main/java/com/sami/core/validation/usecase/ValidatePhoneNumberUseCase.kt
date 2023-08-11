package com.sami.core.validation.usecase

import javax.inject.Inject

class ValidatePhoneNumberUseCase @Inject constructor() {

    private val phoneNumberRegex = Regex("^(077|078|079)\\d{7}$")
    internal operator fun invoke(phoneNumber: String): Boolean {
        return phoneNumberRegex.matches(phoneNumber)
    }
}
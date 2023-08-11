package com.sami.core.validation.usecase

import javax.inject.Inject

class ValidateNationalIdUseCase @Inject constructor() {
    internal operator fun invoke(nationalId: String): Boolean {
        return nationalId.length == 10
    }
}
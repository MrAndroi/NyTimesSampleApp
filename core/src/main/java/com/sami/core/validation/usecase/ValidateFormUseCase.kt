package com.sami.core.validation.usecase

import com.sami.core.validation.model.FormErrors
import javax.inject.Inject

class ValidateFormUseCase @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validateFullNameUseCase: ValidateFullNameUseCase,
    private val validateNationalIdUseCase: ValidateNationalIdUseCase,
    private val validateDateOfBirthUseCase: ValidateDateOfBirthUseCase,
    private val validatePhoneNumberUseCase: ValidatePhoneNumberUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
) {

    operator fun invoke(
        email: String,
        password: String,
        fullName: String? = null,
        phoneNumber: String? = null,
        dateOfBirth: String? = null,
        nationalId: String? = null,
    ): FormErrors {
        return when {
            fullName != null && !validateFullNameUseCase(fullName) -> FormErrors.FULL_NAME
            !validateEmailUseCase(email) -> FormErrors.EMAIL
            nationalId != null && !validateNationalIdUseCase(nationalId) -> FormErrors.NATIONAL_ID
            phoneNumber != null && !validatePhoneNumberUseCase(phoneNumber) -> FormErrors.PHONE_NUMBER
            dateOfBirth != null && !validateDateOfBirthUseCase(dateOfBirth) -> FormErrors.DATE_OF_BIRTH
            !validatePasswordUseCase(password) -> FormErrors.PASSWORD
            else -> FormErrors.NO_ERROR
        }
    }
}
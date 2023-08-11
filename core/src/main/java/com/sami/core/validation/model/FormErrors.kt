package com.sami.core.validation.model

import com.sami.core.R

enum class FormErrors(val errorMessage: Int) {
    EMAIL(R.string.error_message_email),
    FULL_NAME(R.string.error_message_full_name),
    NATIONAL_ID(R.string.error_message_national_id),
    PHONE_NUMBER(R.string.error_message_phone_number),
    PASSWORD(R.string.error_message_password),
    DATE_OF_BIRTH(R.string.error_message_date_of_birth),
    NO_ERROR(-1)
}
package com.sami.features.auth.login.domain.model

import com.sami.features.auth.login.R

enum class LoginErrors(val errorMessage: Int) {
    EMAIL_NOT_REGISTERED(R.string.error_message_email_not_registered),
    INVALID_PASSWORD(R.string.error_message_invalid_password),
    NO_ERROR(-1)
}
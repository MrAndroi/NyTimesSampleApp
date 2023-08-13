package com.sami.core.datastore.models

import kotlinx.serialization.Serializable

@Serializable
data class UserDataModel(
    val email: String,
    val password: String,
    val fullName: String? = null,
    val nationalId: String? = null,
    val phoneNumber: String? = null,
    val dateOfBirth: String? = null,
)

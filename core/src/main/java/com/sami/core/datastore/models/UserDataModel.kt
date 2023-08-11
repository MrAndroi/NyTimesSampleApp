package com.sami.core.datastore.models

import kotlinx.serialization.Serializable

@Serializable
data class UserDataModel(
    val fullName: String,
    val email: String,
    val nationalId: String,
    val phoneNumber: String,
    val dateOfBirth: String,
    val password: String,
)

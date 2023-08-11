package com.sami.core.datastore.models

import kotlinx.serialization.Serializable

@Serializable
data class AppDataModel(
    val users: Set<UserDataModel> = emptySet(),
    val currentUser: UserDataModel? = null
) {

    val isLoggedIn = currentUser != null
}

package com.sami.features.more.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sami.core.datastore.models.UserDataModel
import com.sami.core.datastore.usecase.GetAppDataUseCase
import com.sami.core.datastore.usecase.UpdateAppDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor(
    private val getAppDataUseCase: GetAppDataUseCase,
    private val updateAppDataUseCase: UpdateAppDataUseCase
): ViewModel() {

    private val _userData = MutableStateFlow<UserDataModel?>(null)
    val userData = _userData.asStateFlow()

    private val _logOutUser = MutableSharedFlow<Boolean>()
    val logOutUser = _logOutUser.asSharedFlow()

    init {
        getUserData()
    }
    private fun getUserData() = viewModelScope.launch {
        _userData.emit(getAppDataUseCase().currentUser)
    }

    fun logOut() = viewModelScope.launch {
        updateAppDataUseCase(currentUser = null)
        _logOutUser.emit(true)
    }
}
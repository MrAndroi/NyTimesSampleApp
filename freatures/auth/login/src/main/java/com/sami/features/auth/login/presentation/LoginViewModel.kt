package com.sami.features.auth.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sami.core.validation.model.FormErrors
import com.sami.core.validation.usecase.ValidateFormUseCase
import com.sami.features.auth.login.domain.model.LoginErrors
import com.sami.features.auth.login.domain.usecase.LoginUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase
) : ViewModel() {

    private val _loginErrors = Channel<LoginErrors>()
    val loginErrors = _loginErrors.receiveAsFlow()
    fun validateLoginForm(
        email: String,
        password: String
    ) = viewModelScope.launch {
        _loginErrors.send(loginUserUseCase(email, password))
    }

}
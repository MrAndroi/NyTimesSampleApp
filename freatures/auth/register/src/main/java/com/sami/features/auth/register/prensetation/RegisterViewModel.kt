package com.sami.features.auth.register.prensetation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sami.core.datastore.models.UserDataModel
import com.sami.core.validation.model.FormErrors
import com.sami.core.validation.usecase.ValidateFormUseCase
import com.sami.features.auth.register.domain.usecase.RegisterUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val validateFormUseCase: ValidateFormUseCase,
    private val registerUserUseCase: RegisterUserUseCase,
) : ViewModel() {

    private val _formError = Channel<FormErrors>()
    val formError = _formError.receiveAsFlow()

    private val _registerUser = Channel<Boolean>()
    val registerUser = _registerUser.receiveAsFlow()

    fun validateRegisterForm(
        email: String,
        password: String,
        fullName: String,
        phoneNumber: String,
        dateOfBirth: String,
        nationalId: String,
    ) = viewModelScope.launch {
        _formError.send(validateFormUseCase(email, password, fullName, phoneNumber, dateOfBirth, nationalId))
    }

    fun registerUser(userDataModel: UserDataModel) = viewModelScope.launch {
        _registerUser.send(registerUserUseCase(userDataModel))
    }

}
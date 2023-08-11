package com.sami.features.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sami.core.datastore.usecase.UpdateAppDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val updateAppDataUseCase: UpdateAppDataUseCase
): ViewModel() {

    fun logout() = viewModelScope.launch {
        updateAppDataUseCase(currentUser =  null)
    }
}
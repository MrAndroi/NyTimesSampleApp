package com.sami.features.splash.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sami.core.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(): ViewModel() {

    private val _nextScreen = MutableStateFlow<Screen?>(null)
    val nextScreen = _nextScreen.asStateFlow()

    init {
        navigateToNextScreen()
    }

    private fun navigateToNextScreen() = viewModelScope.launch {
        delay(3000)
        if(true) {
            _nextScreen.emit(Screen.MAIN_ACTIVITY)
        } else {
            _nextScreen.emit(Screen.AUTH_ACTIVITY)
        }
    }

}
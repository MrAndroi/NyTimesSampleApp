package com.sami.features.splash.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sami.core.navigation.Screen
import com.sami.core.navigation.navigateToScreen
import com.sami.features.splash.databinding.ActivitySplashBinding
import com.sami.features.splash.presentation.SplashScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private var _binding: ActivitySplashBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SplashScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        setUpCollectors()
    }

    private fun setUpCollectors() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.nextScreen.collect(::handleNextScreen)
            }
        }
    }

    private fun handleNextScreen(screen: Screen?) {
        screen?.let {
            navigateToScreen(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
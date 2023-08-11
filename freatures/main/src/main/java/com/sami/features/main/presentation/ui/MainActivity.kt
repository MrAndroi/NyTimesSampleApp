package com.sami.features.main.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sami.core.navigation.Screen
import com.sami.core.navigation.navigateToScreen
import com.sami.features.main.databinding.ActivityMainBinding
import com.sami.features.main.presentation.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLogout.setOnClickListener {
            viewModel.logout()
            navigateToScreen(
                screen = Screen.AUTH_ACTIVITY,
                navigateAndRemoveFromStack = true
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
package com.sami.features.auth.base.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sami.features.auth.base.databinding.ActivityAuthenticationBinding
import com.sami.features.auth.base.presentation.ui.adapters.AuthPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthenticationActivity : AppCompatActivity() {

    private var _binding: ActivityAuthenticationBinding? = null
    private val binding get() = _binding!!

    private lateinit var authAdapter: AuthPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        setUpFragments()
    }

    private fun setUpFragments() {
        authAdapter = AuthPagerAdapter(supportFragmentManager, this)
        binding.viewPager.adapter = authAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
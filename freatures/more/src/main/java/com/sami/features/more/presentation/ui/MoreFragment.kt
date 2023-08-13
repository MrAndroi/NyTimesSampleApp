package com.sami.features.more.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sami.core.datastore.models.UserDataModel
import com.sami.core.navigation.Screen
import com.sami.core.navigation.navigateToScreen
import com.sami.features.more.databinding.FragmentMoreBinding
import com.sami.features.more.domain.model.AppLanguages
import com.sami.features.more.presentation.MoreViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoreFragment : Fragment() {

    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MoreViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoreBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setUpCollectors()
        setUpClicks()
    }

    private fun setUpCollectors() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userData.collect(::handleUserData)
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.logOutUser.collect(::handleLogoutState)
            }
        }
    }

    private fun setUpClicks() {
        binding.buttonLogout.setOnClickListener {
            viewModel.logOut()
        }
        binding.buttonChangeLanguage.setOnClickListener {
            changeAppLanguage()
        }
    }

    private fun handleUserData(userData: UserDataModel?) {
        binding.userModel = userData
    }

    private fun handleLogoutState(success: Boolean) {
        if (success) {
            requireActivity().navigateToScreen(
                screen = Screen.AUTH_ACTIVITY,
                navigateAndRemoveFromStack = true
            )
        }
    }

    private fun changeAppLanguage() {
        val currentLang = AppCompatDelegate.getApplicationLocales().toLanguageTags().take(2)
        val newLang = when (AppLanguages.valueOf(currentLang.uppercase())) {
            AppLanguages.EN -> {
                AppLanguages.AR
            }

            AppLanguages.AR -> {
                AppLanguages.EN
            }
        }
        val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(newLang.name.lowercase())
        AppCompatDelegate.setApplicationLocales(appLocale)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
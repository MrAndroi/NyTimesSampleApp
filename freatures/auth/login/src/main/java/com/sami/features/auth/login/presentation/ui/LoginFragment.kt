package com.sami.features.auth.login.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sami.core.navigation.Screen
import com.sami.core.navigation.navigateToScreen
import com.sami.features.auth.login.databinding.FragmentLoginBinding
import com.sami.features.auth.login.domain.model.LoginErrors
import com.sami.features.auth.login.presentation.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setUpCollectors()
        setUpListeners()
        setUpClicks()
    }

    private fun setUpCollectors() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loginErrors.collect(::handleLoginErrors)
            }
        }
    }

    private fun handleLoginErrors(loginErrors: LoginErrors) {
        when (loginErrors) {
            LoginErrors.EMAIL_NOT_REGISTERED -> {
                binding.textInputEmail.error = getString(loginErrors.errorMessage)
            }

            LoginErrors.INVALID_PASSWORD -> {
                binding.textInputPassword.error = getString(loginErrors.errorMessage)
            }

            LoginErrors.NO_ERROR -> {
                requireActivity().navigateToScreen(
                    screen = Screen.MAIN_ACTIVITY,
                    navigateAndRemoveFromStack = true
                )
            }
        }
    }

    private fun setUpListeners() {
        binding.editTextEmail.doAfterTextChanged {
            binding.textInputEmail.isErrorEnabled = false
        }
        binding.editTextPassword.doAfterTextChanged {
            binding.textInputPassword.isErrorEnabled = false
        }
    }

    private fun setUpClicks() {
        binding.buttonRegister.setOnClickListener {
            viewModel.validateLoginForm(
                email = binding.editTextEmail.text.toString(),
                password = binding.editTextPassword.text.toString(),
            )
        }
    }

}
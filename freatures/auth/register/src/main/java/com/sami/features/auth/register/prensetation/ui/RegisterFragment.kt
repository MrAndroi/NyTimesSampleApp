package com.sami.features.auth.register.prensetation.ui

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
import com.sami.core.datastore.models.UserDataModel
import com.sami.core.navigation.Screen
import com.sami.core.navigation.navigateToScreen
import com.sami.core.utils.showMaterialAlertDialog
import com.sami.core.validation.model.FormErrors
import com.sami.features.auth.register.R
import com.sami.features.auth.register.databinding.FragmentRegisterBinding
import com.sami.features.auth.register.prensetation.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(layoutInflater)
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
                viewModel.formError.collect(::handleFormErrors)
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.registerUser.collect(::registerUser)
            }
        }
    }

    private fun registerUser(success: Boolean) {
        if (success) {
            requireActivity().navigateToScreen(
                screen = Screen.MAIN_ACTIVITY,
                navigateAndRemoveFromStack = true
            )
        } else {
            showMaterialAlertDialog(
                context = requireContext(),
                message = getString(R.string.error_message_email_already_registered)
            )
        }
    }

    private fun handleFormErrors(formError: FormErrors) {
        when (formError) {
            FormErrors.FULL_NAME -> {
                binding.textInputFullName.error = getString(formError.errorMessage)
            }

            FormErrors.EMAIL -> {
                binding.textInputEmail.error = getString(formError.errorMessage)
            }

            FormErrors.NATIONAL_ID -> {
                binding.textInputNationalId.error = getString(formError.errorMessage)
            }

            FormErrors.PHONE_NUMBER -> {
                binding.textInputPhoneNumber.error = getString(formError.errorMessage)
            }

            FormErrors.DATE_OF_BIRTH -> {
                binding.textInputDateOfBirth.error = getString(formError.errorMessage)
            }

            FormErrors.PASSWORD -> {
                binding.textInputPassword.error = getString(formError.errorMessage)
            }

            FormErrors.NO_ERROR -> {
                viewModel.registerUser(
                    userDataModel = UserDataModel(
                        fullName = binding.editTextFullName.text.toString(),
                        email = binding.editTextEmail.text.toString(),
                        password = binding.editTextPassword.text.toString(),
                        phoneNumber = binding.editTextPhoneNumber.text.toString(),
                        dateOfBirth = binding.editTextDateOfBirth.text.toString(),
                        nationalId = binding.editTextNationalId.text.toString()
                    )
                )
            }
        }
    }

    private fun setUpListeners() {
        binding.editTextEmail.doAfterTextChanged {
            binding.textInputEmail.isErrorEnabled = false
        }
        binding.editTextFullName.doAfterTextChanged {
            binding.textInputFullName.isErrorEnabled = false
        }
        binding.editTextNationalId.doAfterTextChanged {
            binding.textInputNationalId.isErrorEnabled = false
        }
        binding.editTextPhoneNumber.doAfterTextChanged {
            binding.textInputPhoneNumber.isErrorEnabled = false
        }
        binding.editTextPassword.doAfterTextChanged {
            binding.textInputPassword.isErrorEnabled = false
        }
        binding.editTextDateOfBirth.doAfterTextChanged {
            binding.textInputDateOfBirth.isErrorEnabled = false
        }
    }

    private fun setUpClicks() {
        binding.buttonRegister.setOnClickListener {
            viewModel.validateRegisterForm(
                email = binding.editTextEmail.text.toString(),
                password = binding.editTextPassword.text.toString(),
                fullName = binding.editTextFullName.text.toString(),
                phoneNumber = binding.editTextPhoneNumber.text.toString(),
                dateOfBirth = binding.editTextDateOfBirth.text.toString(),
                nationalId = binding.editTextNationalId.text.toString()
            )
        }
    }
}
package com.umbrella.passwordskeeper.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.umbrella.passwordskeeper.R
import com.umbrella.passwordskeeper.databinding.FragmentAuthBinding
import com.umbrella.passwordskeeper.presentation.utils.ErrorType
import com.umbrella.passwordskeeper.presentation.viewmodels.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthFragment : Fragment() {

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AuthViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonEnter.setOnClickListener {
            val password = binding.passwordInputField.text.toString()
            viewModel.checkInputPassword(password)
        }

        initViewModelObservers()

        viewModel.checkRegistration()
    }

    private fun initViewModelObservers() {
        viewModel.needRegistrationLiveData.observe(viewLifecycleOwner) {
            it?.let {
                showToast(getString(R.string.need_create_password))
                binding.buttonEnter.text = getString(R.string.button_save_password)
                binding.buttonEnter.setOnClickListener {
                    val password = binding.passwordInputField.text.toString()
                    viewModel.createAuthPassword(password)
                }
                viewModel.clearLiveData()
            }
        }

        viewModel.successPasswordLiveData.observe(viewLifecycleOwner) {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_container, PasswordsFragment.newInstance())
                .commit()
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner) { errorType ->
            errorType?.let {
                when (errorType) {
                    ErrorType.CREATED_INCORRECT_PASSWORD -> {
                        showToast(getString(R.string.incorrect_password_1))
                        showToast(getString(R.string.incorrect_password_2))
                    }
                    ErrorType.WRONG_PASSWORD -> {
                        showToast(getString(R.string.wrong_password))
                    }
                    ErrorType.INCORRECT_INPUT_PASSWORD -> {
                        showToast(getString(R.string.empty_password_field))
                    }
                }
                viewModel.clearLiveData()
            }
        }
    }


    private fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
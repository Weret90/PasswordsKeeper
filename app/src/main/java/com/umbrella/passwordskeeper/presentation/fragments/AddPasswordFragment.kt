package com.umbrella.passwordskeeper.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.umbrella.passwordskeeper.R
import com.umbrella.passwordskeeper.databinding.FragmentAddPasswordBinding
import com.umbrella.passwordskeeper.presentation.viewmodels.AddPasswordViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddPasswordFragment : Fragment() {

    private var _binding: FragmentAddPasswordBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddPasswordViewModel by viewModel()

    companion object {
        fun newInstance(): AddPasswordFragment {
            return AddPasswordFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAddPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSavePassword.setOnClickListener {
            val site = binding.siteInputField.text.toString()
            val password = binding.passwordInputField.text.toString()
            viewModel.addNewPassword(site, password)
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }

    private fun initViewModelObservers() {
        viewModel.errorSiteNameLiveData.observe(viewLifecycleOwner) {
            it?.let {
                showToast(getString(R.string.incorrect_site_name))
                viewModel.clearLiveData()
            }
        }

        viewModel.errorPasswordLiveData.observe(viewLifecycleOwner) {
            it?.let {
                showToast(getString(R.string.incorrect_password_1))
                showToast(getString(R.string.incorrect_password_2))
                viewModel.clearLiveData()
            }
        }

        viewModel.successSavePassword.observe(viewLifecycleOwner) {
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
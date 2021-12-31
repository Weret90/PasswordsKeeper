package com.umbrella.passwordskeeper.di

import com.umbrella.passwordskeeper.presentation.viewmodels.AddPasswordViewModel
import com.umbrella.passwordskeeper.presentation.viewmodels.AuthViewModel
import com.umbrella.passwordskeeper.presentation.viewmodels.PasswordsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel {
        AuthViewModel(
            getAuthPasswordUseCase = get(),
            createAuthPasswordUseCase = get(),
            checkAuthPasswordUseCase = get()
        )
    }

    viewModel {
        AddPasswordViewModel(
            addPasswordUseCase = get()
        )
    }

    viewModel {
        PasswordsViewModel(
            getPasswordsListUseCase = get(),
            deletePasswordUseCase = get()
        )
    }
}
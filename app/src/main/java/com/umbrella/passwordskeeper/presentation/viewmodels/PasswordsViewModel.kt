package com.umbrella.passwordskeeper.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umbrella.passwordskeeper.domain.entities.Password
import com.umbrella.passwordskeeper.domain.usecases.DeletePasswordUseCase
import com.umbrella.passwordskeeper.domain.usecases.GetPasswordsListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PasswordsViewModel(
    getPasswordsListUseCase: GetPasswordsListUseCase,
    private val deletePasswordUseCase: DeletePasswordUseCase,
) : ViewModel() {

    val passwordsListLiveData = getPasswordsListUseCase()

    fun deletePassword(password: Password) {
        viewModelScope.launch(Dispatchers.IO) {
            deletePasswordUseCase(password)
        }
    }
}
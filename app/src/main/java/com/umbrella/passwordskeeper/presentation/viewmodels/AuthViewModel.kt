package com.umbrella.passwordskeeper.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umbrella.passwordskeeper.domain.usecases.CheckAuthPasswordUseCase
import com.umbrella.passwordskeeper.domain.usecases.CreateAuthPasswordUseCase
import com.umbrella.passwordskeeper.domain.usecases.GetAuthPasswordUseCase
import com.umbrella.passwordskeeper.presentation.utils.ErrorType
import com.umbrella.passwordskeeper.presentation.utils.PasswordValidator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthViewModel(
    private val getAuthPasswordUseCase: GetAuthPasswordUseCase,
    private val createAuthPasswordUseCase: CreateAuthPasswordUseCase,
    private val checkAuthPasswordUseCase: CheckAuthPasswordUseCase,
) : ViewModel() {

    private val _needRegistrationLiveData = MutableLiveData<Unit?>()
    val needRegistrationLiveData: LiveData<Unit?> = _needRegistrationLiveData

    private val _successPasswordLiveData = MutableLiveData<Unit?>()
    val successPasswordLiveData: LiveData<Unit?> = _successPasswordLiveData

    private val _errorLiveData = MutableLiveData<ErrorType?>()
    val errorLiveData: LiveData<ErrorType?> = _errorLiveData

    fun checkRegistration() {
        val password = getAuthPasswordUseCase()
        if (password == null) {
            _needRegistrationLiveData.value = Unit

        }
    }

    fun createAuthPassword(password: String?) {
        if (PasswordValidator.isValidCreatedPassword(password)) {
            createAuthPasswordUseCase(password!!)
            _successPasswordLiveData.value = Unit
        } else {
            _errorLiveData.value = ErrorType.CREATED_INCORRECT_PASSWORD
        }
    }

    fun checkInputPassword(password: String?) {
        if (PasswordValidator.isValidInputPassword(password)) {
            if (checkAuthPasswordUseCase(password!!)) {
                _successPasswordLiveData.value = Unit
            } else {
                _errorLiveData.value = ErrorType.WRONG_PASSWORD
            }
        } else {
            _errorLiveData.value = ErrorType.INCORRECT_INPUT_PASSWORD
        }
    }

    fun clearLiveData() {
        _needRegistrationLiveData.value = null
        _errorLiveData.value = null
    }
}
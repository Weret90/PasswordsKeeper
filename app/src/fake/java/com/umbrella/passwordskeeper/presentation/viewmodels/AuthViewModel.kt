package com.umbrella.passwordskeeper.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.umbrella.passwordskeeper.domain.usecases.CheckAuthPasswordUseCase
import com.umbrella.passwordskeeper.domain.usecases.CreateAuthPasswordUseCase
import com.umbrella.passwordskeeper.domain.usecases.GetAuthPasswordUseCase
import com.umbrella.passwordskeeper.presentation.utils.ErrorType
import com.umbrella.passwordskeeper.presentation.utils.PasswordValidator

class AuthViewModel(
    private val getAuthPasswordUseCase: GetAuthPasswordUseCase,
    private val createAuthPasswordUseCase: CreateAuthPasswordUseCase,
    private val checkAuthPasswordUseCase: CheckAuthPasswordUseCase,
) : ViewModel() {

    companion object {
        private const val TEST_PASSWORD = "Password14"
    }

    private val _successPasswordLiveData = MutableLiveData<Unit?>()
    val successPasswordLiveData: LiveData<Unit?> = _successPasswordLiveData

    private val _errorLiveData = MutableLiveData<ErrorType?>()
    val errorLiveData: LiveData<ErrorType?> = _errorLiveData

    fun checkInputPassword(password: String?) {
        if (getAuthPasswordUseCase() == null) {
            createAuthPasswordUseCase(TEST_PASSWORD)
        }
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
        _errorLiveData.value = null
    }
}
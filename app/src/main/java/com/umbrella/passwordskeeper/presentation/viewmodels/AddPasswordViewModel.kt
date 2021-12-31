package com.umbrella.passwordskeeper.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umbrella.passwordskeeper.domain.entities.Password
import com.umbrella.passwordskeeper.domain.usecases.AddPasswordUseCase
import com.umbrella.passwordskeeper.presentation.utils.PasswordValidator
import com.umbrella.passwordskeeper.presentation.utils.SiteNameValidator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddPasswordViewModel(
    private val addPasswordUseCase: AddPasswordUseCase
): ViewModel() {

    private val _errorSiteNameLiveData = MutableLiveData<Unit?>()
    val errorSiteNameLiveData: LiveData<Unit?> = _errorSiteNameLiveData

    private val _errorPasswordLiveData = MutableLiveData<Unit?>()
    val errorPasswordLiveData: LiveData<Unit?> = _errorPasswordLiveData

    private val _successSavePassword = MutableLiveData<Unit?>()
    val successSavePassword: LiveData<Unit?> = _successSavePassword

    fun addNewPassword(site: String?, password: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (SiteNameValidator.isValidSiteName(site)
                && PasswordValidator.isValidCreatedPassword(password)
            ) {
                addPasswordUseCase(Password(site!!, password!!))
                withContext(Dispatchers.Main) {
                    _successSavePassword.value = Unit
                }
            } else if (!SiteNameValidator.isValidSiteName(site)) {
                withContext(Dispatchers.Main) {
                    _errorSiteNameLiveData.value = Unit
                }
            } else {
                withContext(Dispatchers.Main) {
                    _errorPasswordLiveData.value = Unit
                }
            }
        }
    }

    fun clearLiveData() {
        _errorPasswordLiveData.value = null
        _errorSiteNameLiveData.value = null
    }
}
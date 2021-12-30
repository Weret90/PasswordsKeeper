package com.umbrella.passwordskeeper.domain.usecases

import androidx.lifecycle.LiveData
import com.umbrella.passwordskeeper.domain.entities.Password
import com.umbrella.passwordskeeper.domain.repositories.PasswordsRepository

class GetPasswordsListUseCase(private val repository: PasswordsRepository) {

    suspend operator fun invoke(): LiveData<List<Password>> {
        return repository.getPasswordsList()
    }
}
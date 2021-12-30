package com.umbrella.passwordskeeper.domain.usecases

import com.umbrella.passwordskeeper.domain.entities.Password
import com.umbrella.passwordskeeper.domain.repositories.PasswordsRepository

class DeletePasswordUseCase(private val repository: PasswordsRepository) {

    suspend operator fun invoke(password: Password) {
        repository.deletePassword(password)
    }
}
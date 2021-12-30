package com.umbrella.passwordskeeper.domain.usecases

import com.umbrella.passwordskeeper.domain.repositories.AuthRepository

class CheckAuthPasswordUseCase(private val repository: AuthRepository) {

    suspend operator fun invoke(password: String): Boolean {
        return repository.checkAuthPassword(password)
    }
}
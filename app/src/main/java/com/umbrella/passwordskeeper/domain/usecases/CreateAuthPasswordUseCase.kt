package com.umbrella.passwordskeeper.domain.usecases

import com.umbrella.passwordskeeper.domain.repositories.AuthRepository

class CreateAuthPasswordUseCase(private val repository: AuthRepository) {

    suspend operator fun invoke(password: String) {
        repository.createAuthPassword(password)
    }
}
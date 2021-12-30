package com.umbrella.passwordskeeper.domain.usecases

import com.umbrella.passwordskeeper.domain.repositories.AuthRepository

class GetAuthPasswordUseCase(private val repository: AuthRepository) {

    suspend operator fun invoke(): String? {
        return repository.getAuthPassword()
    }
}
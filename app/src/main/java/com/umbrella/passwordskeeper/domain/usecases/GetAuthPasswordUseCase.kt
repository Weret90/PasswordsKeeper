package com.umbrella.passwordskeeper.domain.usecases

import com.umbrella.passwordskeeper.domain.repositories.AuthRepository

class GetAuthPasswordUseCase(private val repository: AuthRepository) {

    operator fun invoke(): String? {
        return repository.getAuthPassword()
    }
}
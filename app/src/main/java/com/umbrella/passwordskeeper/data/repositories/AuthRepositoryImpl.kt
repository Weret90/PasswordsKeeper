package com.umbrella.passwordskeeper.data.repositories

import com.umbrella.passwordskeeper.data.local.LocalStorage
import com.umbrella.passwordskeeper.domain.repositories.AuthRepository

class AuthRepositoryImpl(private val local: LocalStorage) : AuthRepository {

    override suspend fun checkAuthPassword(password: String): Boolean {
        return local.getAuthPassword() == password
    }

    override suspend fun createAuthPassword(password: String) {
        local.putAuthPassword(password)
    }

    override suspend fun getAuthPassword(): String? {
        return local.getAuthPassword()
    }
}
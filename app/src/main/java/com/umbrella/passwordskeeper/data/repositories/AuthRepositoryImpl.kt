package com.umbrella.passwordskeeper.data.repositories

import com.umbrella.passwordskeeper.data.local.LocalStorage
import com.umbrella.passwordskeeper.domain.repositories.AuthRepository

class AuthRepositoryImpl(private val local: LocalStorage) : AuthRepository {

    override fun checkAuthPassword(password: String): Boolean {
        return local.getAuthPassword() == password
    }

    override fun createAuthPassword(password: String) {
        local.putAuthPassword(password)
    }

    override fun getAuthPassword(): String? {
        return local.getAuthPassword()
    }
}
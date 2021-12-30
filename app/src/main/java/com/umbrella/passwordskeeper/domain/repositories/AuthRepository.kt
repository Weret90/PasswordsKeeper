package com.umbrella.passwordskeeper.domain.repositories

interface AuthRepository {

    suspend fun checkAuthPassword(password: String): Boolean
    suspend fun createAuthPassword(password: String): Boolean
}
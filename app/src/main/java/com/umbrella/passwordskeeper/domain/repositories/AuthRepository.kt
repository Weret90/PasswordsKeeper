package com.umbrella.passwordskeeper.domain.repositories

interface AuthRepository {

    fun checkAuthPassword(password: String): Boolean
    fun createAuthPassword(password: String)
    fun getAuthPassword(): String?
}
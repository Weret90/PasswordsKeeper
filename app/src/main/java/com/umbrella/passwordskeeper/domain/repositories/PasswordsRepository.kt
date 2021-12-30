package com.umbrella.passwordskeeper.domain.repositories

import androidx.lifecycle.LiveData
import com.umbrella.passwordskeeper.domain.entities.Password

interface PasswordsRepository {

    suspend fun getPasswordsList(): LiveData<List<Password>>
    suspend fun addPassword(password: Password)
    suspend fun deletePassword(password: Password)
}
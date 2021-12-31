package com.umbrella.passwordskeeper.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.umbrella.passwordskeeper.data.local.LocalStorage
import com.umbrella.passwordskeeper.data.mapper.toDataModel
import com.umbrella.passwordskeeper.data.mapper.toDomainModel
import com.umbrella.passwordskeeper.domain.entities.Password
import com.umbrella.passwordskeeper.domain.repositories.PasswordsRepository

class PasswordsRepositoryImpl(private val local: LocalStorage) : PasswordsRepository {

    override  fun getPasswordsList(): LiveData<List<Password>> {
        return Transformations.map(local.getPasswordsList()) {
            it.toDomainModel()
        }
    }

    override suspend fun addPassword(password: Password) {
        local.addPassword(password.toDataModel())
    }

    override suspend fun deletePassword(password: Password) {
        local.deletePassword(password.toDataModel())
    }
}
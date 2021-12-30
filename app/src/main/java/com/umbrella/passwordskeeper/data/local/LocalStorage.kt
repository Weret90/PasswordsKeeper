package com.umbrella.passwordskeeper.data.local

import androidx.lifecycle.LiveData
import com.umbrella.passwordskeeper.data.model.PasswordDTO

interface LocalStorage {

    suspend fun getAuthPassword(): String?
    suspend fun putAuthPassword(password: String)
    suspend fun getPasswordsList(): LiveData<List<PasswordDTO>>
    suspend fun addPassword(password: PasswordDTO)
    suspend fun deletePassword(password: PasswordDTO)
}
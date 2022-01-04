package com.umbrella.passwordskeeper.data.local

import android.content.Context
import androidx.lifecycle.LiveData
import com.umbrella.passwordskeeper.data.local.database.PasswordsDao
import com.umbrella.passwordskeeper.data.model.PasswordDTO

class LocalStorageImpl(context: Context, private val database: PasswordsDao) : LocalStorage {

    companion object {
        private const val SHARED_PREF_NAME = "authorization"
        private const val KEY_PASSWORD = "password"
    }

    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

    override fun getAuthPassword(): String? {
        return sharedPreferences.getString(KEY_PASSWORD, null)
    }

    override fun putAuthPassword(password: String) {
        sharedPreferences.edit().putString(KEY_PASSWORD, password).apply()
    }

    override fun getPasswordsList(): LiveData<List<PasswordDTO>> {
        return database.getAllPasswords()
    }

    override suspend fun addPassword(password: PasswordDTO) {
        database.addPassword(password)
    }

    override suspend fun deletePassword(password: PasswordDTO) {
        database.deletePassword(password)
    }
}
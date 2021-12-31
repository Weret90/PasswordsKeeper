package com.umbrella.passwordskeeper.data.local.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.umbrella.passwordskeeper.data.model.PasswordDTO

@Dao
interface PasswordsDao {

    @Query("SELECT * FROM passwords")
    fun getAllPasswords(): LiveData<List<PasswordDTO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPassword(password: PasswordDTO)

    @Delete
    suspend fun deletePassword(password: PasswordDTO)
}
package com.umbrella.passwordskeeper.data.local.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.umbrella.passwordskeeper.data.model.PasswordDTO

@Dao
interface PasswordsDao {

    @Query("SELECT * FROM passwords")
    fun getAllPasswords(): LiveData<List<PasswordDTO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPassword(password: PasswordDTO)

    @Delete
    fun deletePassword(password: PasswordDTO)
}
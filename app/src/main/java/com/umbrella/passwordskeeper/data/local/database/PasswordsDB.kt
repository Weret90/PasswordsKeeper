package com.umbrella.passwordskeeper.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.umbrella.passwordskeeper.data.model.PasswordDTO

@Database(entities = [PasswordDTO::class], version = 1, exportSchema = false)
abstract class PasswordsDB : RoomDatabase() {

    abstract fun passwordsDao(): PasswordsDao
}
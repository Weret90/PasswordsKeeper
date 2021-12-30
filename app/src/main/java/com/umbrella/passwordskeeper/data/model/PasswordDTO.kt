package com.umbrella.passwordskeeper.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "passwords")
data class PasswordDTO(
    @PrimaryKey
    val site: String,
    val password: String,
)
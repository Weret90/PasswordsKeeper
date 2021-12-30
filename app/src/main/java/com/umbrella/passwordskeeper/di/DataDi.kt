package com.umbrella.passwordskeeper.di

import androidx.room.Room
import com.umbrella.passwordskeeper.data.local.LocalStorage
import com.umbrella.passwordskeeper.data.local.LocalStorageImpl
import com.umbrella.passwordskeeper.data.local.database.PasswordsDB
import com.umbrella.passwordskeeper.data.repositories.AuthRepositoryImpl
import com.umbrella.passwordskeeper.data.repositories.PasswordsRepositoryImpl
import com.umbrella.passwordskeeper.domain.repositories.AuthRepository
import com.umbrella.passwordskeeper.domain.repositories.PasswordsRepository
import org.koin.dsl.module

private const val DB_NAME = "passwords.db"

val repositoriesModule = module {
    single<AuthRepository> {
        AuthRepositoryImpl(local = get())
    }

    single<PasswordsRepository> {
        PasswordsRepositoryImpl(local = get())
    }
}

val localStorageModule = module {
    single<LocalStorage> {
        LocalStorageImpl(context = get(), database = get())
    }

    single {
        Room.databaseBuilder(get(), PasswordsDB::class.java, DB_NAME)
            .build()
            .passwordsDao()
    }
}